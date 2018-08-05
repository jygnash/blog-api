package click.blogs.blogapi.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutUsController {
	private static final Logger logger = LogManager.getLogger(AboutUsController.class);
	
	@RequestMapping(value="/aboutus/pdf", method=RequestMethod.GET, produces = "application/pdf")
	public void getDownload(HttpServletResponse response) throws IOException {
		logger.traceEntry();
		logger.info("------ PDF Downloaded ------'");
		//File pdfFile = new File("src/main/resources/sample.txt");
		File pdfFile = new ClassPathResource("AboutUs.pdf").getFile();
		// Get your file stream from wherever.
		InputStream myStream = new FileInputStream(pdfFile);

		// Set the content type and attachment header.
		//response.addHeader("Content-disposition", "attachment;filename=AboutUs.pdf");
		response.setContentType("application/pdf");

		// Copy the stream to the response's output stream.
		IOUtils.copy(myStream, response.getOutputStream());
		response.flushBuffer();
		logger.traceExit();
	}
}
