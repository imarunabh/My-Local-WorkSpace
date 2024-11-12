package com.files_convert.pdfService;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class PdfService {
	
	private Logger logger= LoggerFactory.getLogger(PdfService.class);
	
	public void createPdf() {
//		logger.info("Create PDF Started: ");
//		String title="Welcome to Learn Code With Durgesh";
//		String content = "We provide content. Please do subscribe";
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		List<String> names = Arrays.asList("Alice","Bob","Charlie");
		Stream<String> nameStream = names.stream();
		
		
		
	}

}
