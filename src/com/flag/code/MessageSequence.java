package com.flag.code;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MessageSequence extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	
	private static final int MISSING = 9999;
	
    public static void main(String[] args) {
    	System.out.println("Its just a test for my personal computer");
    }

	@Override
	public void map(LongWritable arg0, Text arg1, OutputCollector<Text, IntWritable> arg2, Reporter arg3)
			throws IOException {
		// TODO Auto-generated method stub
		
	}
}
