[11:00 AM, 4/23/2020] Nikhil: package mypack;





import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
	
public class TotalAwards{

//MAPPER CODE	
	   
public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
//private final static IntWritable one = new IntWritable(1);
//private final static IntWritable zero = new IntWritable(0);
Text myText = new Text();

public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
	String myString = value.toString(); 
	String[] csvArray = myString.split(",");
	//String check = csvArray[5];
	myText.set("mykey");
	IntWritable amount =new IntWritable(Integer.parseInt(csvArray[3]));
	output.collect(myText,amount);
	
	}  
}

//REDUCER CODE	
public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
public void reduce(Text t_key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException { //{little: {1,1}} 
	int count = 0 ; 
	//String myString = key.toString();
	
	Text key = t_key; 
	while(values.hasNext()) {
		IntWritable value = values.next(); 
		count += value.get(); 
	}
	
	output.collect(key, new IntWritable(count));
	}
}
	
//DRIVER CODE
public static void main(String[] args) throws Exception {
	JobConf conf = new JobConf(TotalAwards.class);
	conf.setJobName("countclass");
	conf.setOutputKeyClass(Text.class);
	conf.setOutputValueClass(IntWritable.class);
	conf.setMapperClass(Map.class);
	conf.setCombinerClass(Reduce.class);
	conf.setReducerClass(Reduce.class);
	conf.setInputFormat(TextInputFormat.class);
	conf.setOutputFormat(TextOutputFormat.class); 
	FileInputFormat.setInputPaths(conf, new Path(args[0]));
	FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	JobClient.runJob(conf);   
}
}
[11:01 AM, 4/23/2020] Nikhil: 
