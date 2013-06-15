
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 */
	public static void main(String[] args) {
		
		try {
			test1();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * parse an existed file
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void test1() throws ParserConfigurationException, SAXException, IOException, TransformerException 
	{
		/*----------------------
		 * Build up the tools
		 *----------------------*/
		// javax.xml.parsers.DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// not javax.xml.bind.Element;
		// but com.w3c.dom.Element
		Element root = null;
		// javax.xml.parsers.DocuementBuilder
		DocumentBuilder db = factory.newDocumentBuilder();
		// org.w3c.dom.Document
		Document xmlDoc = db.parse( new File( "test.xml" ) );
		
		
		/*----------------------
		 * Get the nodes
		 *----------------------*/
		// get root
		root = xmlDoc.getDocumentElement();
		System.out.println( root.getNodeName() );
		// get a certain node
		// org.w3c.dom.NodeList
		NodeList list = xmlDoc.getElementsByTagName( "test2" );
		// NOTICE: \n in text content can be shown in System.out.println()
		System.out.println( list.item(2).getTextContent() );
		
		/*----------------------
		 * Insert new node
		 *----------------------*/
		Node ntest4 = xmlDoc.createElement( "test4" );
		ntest4.setTextContent( "new content" );
		root.appendChild( ntest4 );
		System.out.println( xmlDoc.getElementsByTagName( "test4" ).item(0).getTextContent() );
		
		/*----------------------
		 * Delete node
		 *----------------------*/
		
		/*----------------------
		 * Write back to file
		 *----------------------*/
		// javax.xml.transform.TransformerFactory
		TransformerFactory tf = TransformerFactory.newInstance();
		// javax.xml.transform.Transformer
		Transformer t = tf.newTransformer();
		t.setOutputProperty( "indent", "yes" );
		// javax.xml.transform.dom.DOMSource
		DOMSource source = new DOMSource();
		source.setNode( xmlDoc );
		// javax.xml.transform.stream.StreamResult
		StreamResult result = new StreamResult();
		result.setOutputStream( new FileOutputStream( "test2.xml" ) );
		t.transform( source, result );
	}

	/**
	 * create from empty
	 * @throws ParserConfigurationException
	 * @throws FileNotFoundException 
	 * @throws TransformerException 
	 */
	public static void test2() throws ParserConfigurationException, FileNotFoundException, TransformerException
	{
		/*----------------------
		 * Build up the tools
		 *----------------------*/
		// javax.xml.parsers.DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// not javax.xml.bind.Element;
		// but com.w3c.dom.Element
		Element root = null;
		// javax.xml.parsers.DocuementBuilder
		DocumentBuilder db = factory.newDocumentBuilder();
		// org.w3c.dom.Document
		Document xmlDoc = db.newDocument();
		
		Element e1 = xmlDoc.createElement( "root" );
		xmlDoc.appendChild( e1 );
		Element e2 = xmlDoc.createElement( "level1" );
		e2.setTextContent( "level1 text" );
		e1.appendChild( e2 );
		Element e3 = xmlDoc.createElement( "level1" );
		e3.setTextContent( "level2 text" );
		e1.appendChild( e3 );
		
		/*----------------------
		 * Write back to file
		 *----------------------*/
		// javax.xml.transform.TransformerFactory
		TransformerFactory tf = TransformerFactory.newInstance();
		// javax.xml.transform.Transformer
		Transformer t = tf.newTransformer();
		t.setOutputProperty( "indent", "yes" );
		// javax.xml.transform.dom.DOMSource
		DOMSource source = new DOMSource();
		source.setNode( xmlDoc );
		// javax.xml.transform.stream.StreamResult
		StreamResult result = new StreamResult();
		result.setOutputStream( new FileOutputStream( "test3.xml" ) );
		t.transform( source, result );
	}
}
