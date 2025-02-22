package ms.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {


	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException 
	{
		//write utility to scan json , retrieve values based on your requirement
		//read JSON to string
		String jsonContent =FileUtils.readFileToString(new File (filePath),StandardCharsets.UTF_8);
	//get jackson databind dependency and add in pom.xml file
		ObjectMapper mapper= new ObjectMapper();
		//create object of objectmapper class 
		List<HashMap<String,String>> data =mapper.readValue(jsonContent , new TypeReference<List<HashMap<String,String>>>(){});
		//convert string to hashmap using Jackson databind and convert  to list
		return data;
	}
}
