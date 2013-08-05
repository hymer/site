package com.hymer.report.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hymer.contract.dao.ContractDAO;
import com.hymer.contract.entity.Contract;
import com.hymer.core.CommonService;
import com.hymer.core.model.Condition;
import com.hymer.core.model.ResponseJSON;
import com.hymer.core.util.Formatters;
import com.hymer.finacing.dao.PaymentDAO;
import com.hymer.finacing.entity.Payment;

@Service
public class ReportService extends CommonService {

	@Autowired
	private ContractDAO contractDAO;
	@Autowired
	private PaymentDAO paymentDAO;

	/**
	 * 
	 * @return
	 */
	public ResponseJSON month(int year, int month) {
		ResponseJSON json = new ResponseJSON();

		// 起止日期
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();

		startDate.set(year, month - 1, 1, 0, 0, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		endDate.set(Calendar.YEAR, year);
		endDate.set(Calendar.MONTH, month - 1);
		int dayOfThisMonth = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		endDate.set(year, month - 1, dayOfThisMonth, 23, 23, 59);
		endDate.set(Calendar.MILLISECOND, 999);

		log.info("startDate=" + Formatters.formatTime(startDate.getTime()));
		log.info("endDate=" + Formatters.formatTime(endDate.getTime()));

		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate.getTime());
		dates.add(endDate.getTime());

		List<Integer> months = new ArrayList<Integer>();
		double[] amounts = new double[dayOfThisMonth];
		double[] amounts2 = new double[dayOfThisMonth];
		double[] amounts3 = new double[dayOfThisMonth];
		double[] amounts4 = new double[dayOfThisMonth];

		// 采购合同款
		Condition condition = new Condition("signDate", dates);
		condition.setOperator(Condition.BETWEEN);
		condition.setValueType(Date.class);
		Condition condition2 = new Condition("contractType",
				Contract.TYPE_PURCHASE);
		List<Contract> contracts = contractDAO.getByCondition(condition,
				condition2);
		for (int i = 1; i <= dayOfThisMonth; i++) {
			months.add(i);
		}
		for (Contract contract : contracts) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int m = c.get(Calendar.DATE) - 1;
			double d = amounts[m];
			d = d + contract.getAmount();
			amounts[m] = d;
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "采购合同款");
		map1.put("data", amounts);

		// 销售合同款
		Condition condition3 = new Condition("contractType",
				Contract.TYPE_SALES);
		List<Contract> contracts2 = contractDAO.getByCondition(condition,
				condition3);
		for (Contract contract : contracts2) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int m = c.get(Calendar.DATE) - 1;
			Double d = amounts2[m];
			d = d + contract.getAmount();
			amounts2[m] = d;
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "销售合同款");
		map2.put("data", amounts2);

		// 采购付款
		Condition condition4 = new Condition("dealDate", dates);
		condition4.setOperator(Condition.BETWEEN);
		List<Payment> payments = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_PURCHASE));
		for (Payment payment : payments) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int m = c.get(Calendar.DATE) - 1;
			Double d = amounts3[m];
			d = d + payment.getAmount();
			amounts3[m] = d;
		}
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "采购付款");
		map3.put("data", amounts3);

		// 销售额
		List<Payment> payments2 = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_SALES));
		for (Payment payment : payments2) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int m = c.get(Calendar.DATE) - 1;
			Double d = amounts4[m];
			d = d + payment.getAmount();
			amounts4[m] = d;
		}
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "销售额");
		map4.put("data", amounts4);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);

		json.put("name", year + "年" + month + "月财务报表");
		json.put("cats", months);
		json.put("data", data);

		return json;
	}

	public ResponseJSON year(int year) {
		ResponseJSON json = new ResponseJSON();
		// 起止日期
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();

		startDate.set(year, 0, 1, 0, 0, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		endDate.set(Calendar.YEAR, year);
		endDate.set(Calendar.MONTH, 11);
		int dayOfThisMonth = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		endDate.set(year, 11, dayOfThisMonth, 23, 23, 59);
		endDate.set(Calendar.MILLISECOND, 999);

		log.info("startDate=" + Formatters.formatTime(startDate.getTime()));
		log.info("endDate=" + Formatters.formatTime(endDate.getTime()));

		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate.getTime());
		dates.add(endDate.getTime());

		List<Integer> months = new ArrayList<Integer>();
		double[] amounts = new double[12];
		double[] amounts2 = new double[12];
		double[] amounts3 = new double[12];
		double[] amounts4 = new double[12];

		// 采购合同款
		Condition condition = new Condition("signDate", dates);
		condition.setOperator(Condition.BETWEEN);
		condition.setValueType(Date.class);
		Condition condition2 = new Condition("contractType",
				Contract.TYPE_PURCHASE);
		List<Contract> contracts = contractDAO.getByCondition(condition,
				condition2);
		for (int i = 1; i <= 12; i++) {
			months.add(i);
		}
		for (Contract contract : contracts) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int m = c.get(Calendar.MONTH);
			double d = amounts[m];
			d = d + contract.getAmount();
			amounts[m] = d;
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "采购合同款");
		map1.put("data", amounts);

		// 销售合同款
		Condition condition3 = new Condition("contractType",
				Contract.TYPE_SALES);
		List<Contract> contracts2 = contractDAO.getByCondition(condition,
				condition3);
		for (Contract contract : contracts2) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int m = c.get(Calendar.MONTH);
			Double d = amounts2[m];
			d = d + contract.getAmount();
			amounts2[m] = d;
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "销售合同款");
		map2.put("data", amounts2);

		// 采购付款
		Condition condition4 = new Condition("dealDate", dates);
		condition4.setOperator(Condition.BETWEEN);
		List<Payment> payments = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_PURCHASE));
		for (Payment payment : payments) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int m = c.get(Calendar.MONTH);
			Double d = amounts3[m];
			d = d + payment.getAmount();
			amounts3[m] = d;
		}
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "采购付款");
		map3.put("data", amounts3);

		// 销售额
		List<Payment> payments2 = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_SALES));
		for (Payment payment : payments2) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int m = c.get(Calendar.MONTH);
			Double d = amounts4[m];
			d = d + payment.getAmount();
			amounts4[m] = d;
		}
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "销售额");
		map4.put("data", amounts4);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);

		json.put("name", year + "年财务报表");
		json.put("cats", months);
		json.put("data", data);

		return json;
	}

	public ResponseJSON lastOneYear() {
		ResponseJSON json = new ResponseJSON();
		// 起止日期
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();

		startDate.add(Calendar.YEAR, -1);
		startDate.add(Calendar.MONTH, 1);

		log.info("startDate=" + Formatters.formatTime(startDate.getTime()));
		log.info("endDate=" + Formatters.formatTime(endDate.getTime()));

		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate.getTime());
		dates.add(endDate.getTime());

//		Map<String, Integer> months = new HashMap<String, Integer>();
		String[] months = new String[12];
		double[] amounts = new double[12];
		double[] amounts2 = new double[12];
		double[] amounts3 = new double[12];
		double[] amounts4 = new double[12];

		Calendar tmp = Calendar.getInstance();
		tmp.setTime(startDate.getTime());
		for (int i = 0; i < 12; i++) {
			int year = tmp.get(Calendar.YEAR);
			int month = tmp.get(Calendar.MONTH) + 1;
			String mm = year + "/" + month;
//			months.put(mm, i);
			months[i] = mm;
			if (month == 12) {
				tmp.add(Calendar.YEAR, 1);
				tmp.set(Calendar.MONTH, 0);
			} else {
				tmp.add(Calendar.MONTH, 1);
			}
		}

		// 采购合同款
		Condition condition = new Condition("signDate", dates);
		condition.setOperator(Condition.BETWEEN);
		condition.setValueType(Date.class);
		Condition condition2 = new Condition("contractType",
				Contract.TYPE_PURCHASE);
		List<Contract> contracts = contractDAO.getByCondition(condition,
				condition2);
		for (Contract contract : contracts) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String mm = year + "/" + month;
//			int m = months.get(mm);
			int m = getIndex(mm, months);

			double d = amounts[m];
			d = d + contract.getAmount();
			amounts[m] = d;
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "采购合同款");
		map1.put("data", amounts);

		// 销售合同款
		Condition condition3 = new Condition("contractType",
				Contract.TYPE_SALES);
		List<Contract> contracts2 = contractDAO.getByCondition(condition,
				condition3);
		for (Contract contract : contracts2) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String mm = year + "/" + month;
			int m = getIndex(mm, months);
			Double d = amounts2[m];
			d = d + contract.getAmount();
			amounts2[m] = d;
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "销售合同款");
		map2.put("data", amounts2);

		// 采购付款
		Condition condition4 = new Condition("dealDate", dates);
		condition4.setOperator(Condition.BETWEEN);
		List<Payment> payments = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_PURCHASE));
		for (Payment payment : payments) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String mm = year + "/" + month;
			int m = getIndex(mm, months);
			Double d = amounts3[m];
			d = d + payment.getAmount();
			amounts3[m] = d;
		}
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "采购付款");
		map3.put("data", amounts3);

		// 销售额
		List<Payment> payments2 = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_SALES));
		for (Payment payment : payments2) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String mm = year + "/" + month;
			int m = getIndex(mm, months);
			Double d = amounts4[m];
			d = d + payment.getAmount();
			amounts4[m] = d;
		}
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "销售额");
		map4.put("data", amounts4);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);

		json.put(
				"name",
				Formatters.formatDate(startDate.getTime()).substring(0, 7)
						+ " 至 "
						+ Formatters.formatDate(endDate.getTime()).substring(0,
								7) + " 财务报表");
//		Object[] catas = months.keySet().toArray();
//		Arrays.sort(catas);
//		json.put("cats", catas);
		json.put("cats", months);
		json.put("data", data);
		return json;
	}

	public ResponseJSON last15DaysMoney() {
		ResponseJSON json = new ResponseJSON();
		json.put("name", "近15日 财务报表");

		// 起止日期
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();

		startDate.add(Calendar.DATE, -14);
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);

		log.info("startDate=" + Formatters.formatTime(startDate.getTime()));
		log.info("endDate=" + Formatters.formatTime(endDate.getTime()));

		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate.getTime());
		dates.add(endDate.getTime());

		String[] months = new String[15];
		double[] amounts = new double[15];
		double[] amounts2 = new double[15];
		double[] amounts3 = new double[15];
		double[] amounts4 = new double[15];

		Calendar tmp = Calendar.getInstance();
		tmp.setTime(startDate.getTime());
		for (int i = 0; i < 15; i++) {
			int month = tmp.get(Calendar.MONTH) + 1;
			int day = tmp.get(Calendar.DATE);
			String mm = month + "/" + day;
			months[i] = mm;
			tmp.add(Calendar.DATE, 1);
		}

		// 采购合同款
		Condition condition = new Condition("signDate", dates);
		condition.setOperator(Condition.BETWEEN);
		condition.setValueType(Date.class);
		Condition condition2 = new Condition("contractType",
				Contract.TYPE_PURCHASE);
		List<Contract> contracts = contractDAO.getByCondition(condition,
				condition2);
		for (Contract contract : contracts) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);

			double d = amounts[m];
			d = d + contract.getAmount();
			amounts[m] = d;
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "采购合同款");
		map1.put("data", amounts);

		// 销售合同款
		Condition condition3 = new Condition("contractType",
				Contract.TYPE_SALES);
		List<Contract> contracts2 = contractDAO.getByCondition(condition,
				condition3);
		for (Contract contract : contracts2) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);
			Double d = amounts2[m];
			d = d + contract.getAmount();
			amounts2[m] = d;
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "销售合同款");
		map2.put("data", amounts2);

		// 采购付款
		Condition condition4 = new Condition("dealDate", dates);
		condition4.setOperator(Condition.BETWEEN);
		List<Payment> payments = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_PURCHASE));
		for (Payment payment : payments) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);
			Double d = amounts3[m];
			d = d + payment.getAmount();
			amounts3[m] = d;
		}
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "采购付款");
		map3.put("data", amounts3);

		// 销售额
		List<Payment> payments2 = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_SALES));
		for (Payment payment : payments2) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);
			Double d = amounts4[m];
			d = d + payment.getAmount();
			amounts4[m] = d;
		}
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "销售额");
		map4.put("data", amounts4);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);

		json.put("cats", months);
		json.put("data", data);
		return json;
	}

	public ResponseJSON last15DaysCount() {
		ResponseJSON json = new ResponseJSON();
		json.put("name", "近15日 交易数报表");

		// 起止日期
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();

		startDate.add(Calendar.DATE, -14);
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);

		log.info("startDate=" + Formatters.formatTime(startDate.getTime()));
		log.info("endDate=" + Formatters.formatTime(endDate.getTime()));

		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate.getTime());
		dates.add(endDate.getTime());

		String[] months = new String[15];
		int[] amounts = new int[15];
		int[] amounts2 = new int[15];
		int[] amounts3 = new int[15];
		int[] amounts4 = new int[15];

		Calendar tmp = Calendar.getInstance();
		tmp.setTime(startDate.getTime());
		for (int i = 0; i < 15; i++) {
			int month = tmp.get(Calendar.MONTH) + 1;
			int day = tmp.get(Calendar.DATE);
			String mm = month + "/" + day;
			months[i] = mm;
			tmp.add(Calendar.DATE, 1);
		}

		// 采购合同款
		Condition condition = new Condition("signDate", dates);
		condition.setOperator(Condition.BETWEEN);
		condition.setValueType(Date.class);
		Condition condition2 = new Condition("contractType",
				Contract.TYPE_PURCHASE);
		List<Contract> contracts = contractDAO.getByCondition(condition,
				condition2);
		for (Contract contract : contracts) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);

			int d = amounts[m];
			d++;
			amounts[m] = d;
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "采购合同数");
		map1.put("data", amounts);

		// 销售合同款
		Condition condition3 = new Condition("contractType",
				Contract.TYPE_SALES);
		List<Contract> contracts2 = contractDAO.getByCondition(condition,
				condition3);
		for (Contract contract : contracts2) {
			Calendar c = Calendar.getInstance();
			c.setTime(contract.getSignDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);
			int d = amounts[m];
			d++;
			amounts2[m] = d;
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "销售合同数");
		map2.put("data", amounts2);

		// 采购付款
		Condition condition4 = new Condition("dealDate", dates);
		condition4.setOperator(Condition.BETWEEN);
		List<Payment> payments = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_PURCHASE));
		for (Payment payment : payments) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);
			int d = amounts[m];
			d++;
			amounts3[m] = d;
		}
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "付款笔数");
		map3.put("data", amounts3);

		// 销售额
		List<Payment> payments2 = paymentDAO.getByCondition(condition4,
				new Condition("paymentType", Contract.TYPE_SALES));
		for (Payment payment : payments2) {
			Calendar c = Calendar.getInstance();
			c.setTime(payment.getDealDate());
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String mm = month + "/" + day;
			int m = getIndex(mm, months);
			int d = amounts[m];
			d++;
			amounts4[m] = d;
		}
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "收款笔数");
		map4.put("data", amounts4);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);

		json.put("cats", months);
		json.put("data", data);
		return json;
	}

	public int getIndex(String value, String[] map) {
		for (int i = 0; i < map.length; i++) {
			if (value.equals(map[i])) {
				return i;
			}
		}
		return -1;
	}
}
