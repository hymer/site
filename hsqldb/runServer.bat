cd ./data
@java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server -port 9001 -database.0 file:contract -dbname.0 contract
