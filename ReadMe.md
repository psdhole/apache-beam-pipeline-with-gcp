#apache-beam-data-flow-runner-demo


## Run below command to set our GCP account credentials in current CMD session.

   cd <path>/apache-beam-data-flow-runner-demo
   set GOOGLE_APPLICATION_CREDENTIALS=prj-google-service.json
   
   Note : 'path' - location of the project as below
   	
   e.g.
   		cd D:\gitrepo\apache-beam-data-flow-runner-demo
   		set GOOGLE_APPLICATION_CREDENTIALS=prj-google-service.json
	
	
## Run below command create the template for our beam pipeline template on the GCP.

   cd <path>/apache-beam-data-flow-runner-demo
   
   mvn compile exec:java 
   -Dexec.mainClass=com.beam.data.flow.runner.demo.FileToFileDemo 	
   -Dexec.args="--runner=DataflowRunner       	
		--project=striking-shift-248504 	
		--stagingLocation=gs://praj-demo-bucket/staging/prj-file-to-file  		--templateLocation=gs://praj-demo-bucket/templates/prj-file-to-file-template"

## Run above the template using data flow runner job on GCP environment. 
			
	
		
	



