package site.document;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @auther:
 * @date:
 * @describtion:
 **/


public class DocumentTest {

    @Test
    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = DocumentTest.class.getClassLoader();

        String path = classLoader.getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path),"utf-8");
        Elements elements = document.getElementsByTag("student");
        Element element1 = document.getElementsByTag("student").get(0);
        String number = element1.attr("number");

        Elements elements2 = document.select("name");




        //System.out.println(element);

        //String path = DocumentTest.class.getClassLoader().getResource("student.xml").getPath();


       // System.out.println(path);

    }

}
