package com.lyz.code.infinity.database;

import java.util.Properties;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;

public class ParseXML {
	// ����һ��Properties ������� dbhost dbuser dbpassword��ֵ
	private Properties props;

	// �����props
	public Properties getProps() {
		return this.props;
	}

	public synchronized void parse(String filename) {
		// �����ǵĽ���������
		ConfigParser handler = new ConfigParser();
		// ��ȡSAX��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);
		// ��ȡSAX����
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		URL confURL = null;
		// �õ������ļ�config.xml����Ŀ¼eclipse������WEB-INF/classes/config.xml,

		// ��config.xml�������classes�ļ����У�����ᱨjava.lang.NullPointerException����
		try {
			confURL = ParseXML.class.getClassLoader().getResource(filename);
			// ֻ��Ҫ����������Ҫ��XML�ļ����������ȥ�Ϳ����ˣ�
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		try { // ���������ͽ�������config.xml��ϵ����,��ʼ����
			parser.parse(confURL.toString(), handler);
			// ��ȡ�����ɹ�������� �Ժ� ��������Ӧ�ó���ֻҪ���ñ������props�Ϳ�����ȡ���������ƺ�ֵ��
			props = handler.getProps();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			factory = null;
			parser = null;
			handler = null;
		}
	}
}
