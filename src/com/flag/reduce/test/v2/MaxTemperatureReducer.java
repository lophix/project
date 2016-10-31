package com.flag.reduce.test.v2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
		int maxValue = Integer.MIN_VALUE;
		for(IntWritable value : arg1){
			maxValue = Math.max(maxValue, value.get());
		}
		arg2.write(arg0, new IntWritable(maxValue));
	}

}
