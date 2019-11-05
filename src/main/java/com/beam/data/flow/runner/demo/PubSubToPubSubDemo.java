/*
 * 
 */
package com.beam.data.flow.runner.demo;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PubSubToPubSubDemo.
 */
public class PubSubToPubSubDemo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PubSubToPubSubDemo.class);

	/** The Constant INPUT_TOPIC_NAME. */
	private static final String INPUT_TOPIC_NAME = "projects/striking-shift-248504/topics/prj-data-topic";

	/** The Constant OUTPUT_TOPIC_NAME. */
	private static final String OUTPUT_TOPIC_NAME = "projects/striking-shift-248504/topics/prj-data-topic-2";

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.fromArgs(args).withValidation().create());
		pipeline.apply(PubsubIO.readStrings().fromTopic(INPUT_TOPIC_NAME))
				.apply(MapElements.via(new SimpleFunction<String, String>() {
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(String record) {
						LOGGER.debug("#Processing record : {} ", record);
						return record;
					}
				})).apply(PubsubIO.writeStrings().to(OUTPUT_TOPIC_NAME));
		pipeline.run();
		LOGGER.debug("All done!!");
	}
}
