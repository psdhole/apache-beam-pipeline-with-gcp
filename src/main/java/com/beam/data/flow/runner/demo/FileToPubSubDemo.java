/*
 * 
 */
package com.beam.data.flow.runner.demo;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FileToPubSubDemo.
 */
public class FileToPubSubDemo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileToPubSubDemo.class);

	/** The Constant INPUT_FILE_NAME. */
	private static final String INPUT_FILE_NAME = "gs://praj-demo-bucket/input/input.data";

	/** The Constant OUTPUT_TOPIC_NAME. */
	private static final String OUTPUT_TOPIC_NAME = "projects/striking-shift-248504/topics/prj-data-topic";

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.fromArgs(args).withValidation().create());
		PCollection<String> input = pipeline.apply(TextIO.read().from(INPUT_FILE_NAME));
		PCollection<String> output = input.apply("Read and write the file", ParDo.of(new DoFn<String, String>() {
			private static final long serialVersionUID = 1L;

			/**
			 * Process element.
			 *
			 * @param context
			 *            the context
			 */
			@ProcessElement
			public void processElement(ProcessContext context) {
				LOGGER.debug("#Processing record : {}", context.element());
				context.output(context.element());
			}
		}));
		output.apply(PubsubIO.writeStrings().to(OUTPUT_TOPIC_NAME));
		pipeline.run();
		LOGGER.debug("All done!!");
	}
}
