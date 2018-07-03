package com.zjy.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

public class MyHtmlCompress {
	 /** 将html字符串进行压缩 */

	public String htmlCompress(String text) throws Exception {
	    HtmlCompressor compressor = new HtmlCompressor();
	    compressor.setEnabled(true);
	    compressor.setCompressCss(true);
	    //TODO js 压缩还需处理
	    compressor.setYuiJsPreserveAllSemiColons(true);
	    compressor.setYuiJsLineBreak(1);
	    compressor.setPreserveLineBreaks(false);
	    compressor.setRemoveIntertagSpaces(true);
	    compressor.setRemoveComments(true);
	    compressor.setRemoveMultiSpaces(true);

	    
	    StringBuffer htmlBuf = new StringBuffer();
	    readFileToBuffer(text, htmlBuf);
	    String compressedStr = compressor.compress(htmlBuf.toString());
	    return compressedStr;
	}
	private void readFileToBuffer(String outText, StringBuffer sb) {
	    byte[] bytes = outText.getBytes();
	    try {
	        InputStream inputStream = new ByteArrayInputStream(bytes);
	        InputStreamReader ir = new InputStreamReader(inputStream);
	        BufferedReader br = new BufferedReader(ir);
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }
	        inputStream.close();
	        ir.close();
	        br.close();
	    } catch (IOException e) {
	       // LOG.error(e);
	    }
	}
}
