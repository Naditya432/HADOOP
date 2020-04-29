# HADOOP MAP REDUCE DEMONSTARIONS

STEPS TO EXCEUTE
1. Run all the hadoop daemons by the following commands:
● cd $HADOOP_HOME/sbin
● ./start-all.sh2. Create an input directory in hdfs:
● hdfs dfs -mkdir -p ~/<input_filename>
3. Move the .csv file into the input directory.
● hdfs dfs -put <local_path_of_csvfile> ~/<input_filename>/
4. Execute the job.
● hadoop jar <path_of_exported_jar_file> ~/<input_filename> ~/<output_filename>
5. Obtain the results from.
●
hdfs dfs -cat ~/<output_filename>/part*
