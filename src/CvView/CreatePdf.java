package CvView;

import CvView.*;
import controller.Login;
import model.Member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class CreatePdf {
	private static Logger logger = LogManager.getLogger(CreatePdf.class);

	public static void createPDF(CvView.CvContent content,HttpServletRequest req,Member m)
	{
		logger.info("createPDF method | started.");
		Document document = new Document(PageSize.A4);
		try {
			File f = new File(req.getRealPath("")+"/" + content.getPersonal().getPersonalName().replaceAll("\\s+","") + "_resume.pdf");
			if(!f.exists()){
				
				 System.out.println("is Created PDF File : "+f.createNewFile());
			}else{
				System.out.println("Aynı PDF dosyasından var");
			}
			
			PdfWriter w = PdfWriter.getInstance(document, new FileOutputStream(f));
			
			   
			document.open();

			Image img2 = Image.getInstance("32bitResume3.png");
			img2.scaleAbsolute(180f, 32f);
			img2.setAlignment(Image.MIDDLE);
			document.add(img2);
			document.add(new Paragraph(" "));

			PdfPCell emptyCell = new PdfPCell();
			emptyCell.setBorder(Rectangle.NO_BORDER);

			// entry part - photo & name & objectives
			
			PdfPTable table = null;
			try // if member dont use profil picture, column settings will change.
			{
				table = new PdfPTable(2);
				table.setWidthPercentage(100);
				table.setWidths(new int[] { 3, 1 });
				table.addCell(textOfEntryPart(content.getPersonal()));
				table.addCell(createImageCell("http://localhost:8080/CvSystem/" + content.getPersonal().getPersonalPhoto()));

			}
			catch (Exception e) {
				table = new PdfPTable(1);
				table.setWidthPercentage(100);
				//PdfPCell cell = new PdfPCell(new Phrase(" "));
				//cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(textOfEntryPart(content.getPersonal()));
			}
			document.add(table);
			document.add(new Chunk(new DottedLineSeparator()));

			// personal info

			PdfPTable table2 = new PdfPTable(2);

			table2.setWidthPercentage(100);

			table2.setWidths(new int[] { 7, 17 });

			PdfPCell cell = new PdfPCell();

			cell.addElement(new Phrase(new Chunk("PERSONAL INFO", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));

			cell.setBorder(Rectangle.NO_BORDER);

			table2.addCell(cell);

			table2.addCell(personalInfo(content.getPersonal()));

			document.add(table2);

			document.add(new Chunk(new DottedLineSeparator()));

			System.out.println("jop  baslangc " + w.getVerticalPosition(false) + " " + w.getVerticalPosition(true));

			// job experience

			if (content.getJobExperience().getJobCompanyName().size() > 0)

			{
				PdfPTable table3 = new PdfPTable(2);
				table3.setWidthPercentage(100);
				table3.setWidths(new int[] { 7, 17 });
				PdfPCell cell3 = new PdfPCell();
				cell3.addElement(
						new Phrase(new Chunk("JOP EXPERIENCE", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));
				cell3.setBorder(Rectangle.NO_BORDER);
				table3.addCell(cell3);
				table3.addCell(jopExperience(content.getJobExperience()));
				document.add(table3);
				document.add(new Chunk(new DottedLineSeparator()));
			}


			// education
			if (content.getEducation().getEduSchoolName().size() > 0) {
				PdfPTable table4 = new PdfPTable(2);
				table4.setWidthPercentage(100);
				table4.setWidths(new int[] { 7, 17 });
				PdfPCell cell4 = new PdfPCell();
				cell4.addElement(new Phrase(new Chunk("EDUCATION", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));
				cell4.setBorder(Rectangle.NO_BORDER);

				table4.addCell(cell4);
				table4.addCell(education(content.getEducation()));
				document.add(table4);
				document.add(new Chunk(new DottedLineSeparator()));
			}



			// foreign language
			if (content.getForeign().getForeignName().size() > 0) {
				PdfPTable table5 = new PdfPTable(2);
				table5.setWidthPercentage(100);
				table5.setWidths(new int[] { 7, 17 });
				PdfPCell cell5 = new PdfPCell();
				cell5.addElement(
						new Phrase(new Chunk("FOREIGN LANGUAGE", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));
				cell5.setBorder(Rectangle.NO_BORDER);
				table5.addCell(cell5);
				table5.addCell(foreignLanguage(content.getForeign()));
				document.add(table5);
				document.add(new Chunk(new DottedLineSeparator()));

			}


			// Skills

			if (!content.getSkill().getSkillDescription().equals("")) {
				PdfPTable table6 = new PdfPTable(2);
				table6.setWidthPercentage(100);
				table6.setWidths(new int[] { 7, 17 });
				PdfPCell cell6 = new PdfPCell();
				cell6.addElement(new Phrase(new Chunk("SKILLS", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));
				cell6.setBorder(Rectangle.NO_BORDER);
				table6.addCell(cell6);
				table6.addCell(skills(content.getSkill()));
				document.add(table6);
				document.add(new Chunk(new DottedLineSeparator()));

			}

			// cources and seminers

			if (!content.getCourses().getCoursesDescription().equals("")) {
				PdfPTable table7 = new PdfPTable(2);
				table7.setWidthPercentage(100);
				table7.setWidths(new int[] { 7, 17 });
				PdfPCell cell7 = new PdfPCell();
				cell7.addElement(
						new Phrase(new Chunk("COURCES SEMINERS", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));
				cell7.setBorder(Rectangle.NO_BORDER);
				table7.addCell(cell7);
				table7.addCell(coursesAndSeminars(content.getCourses()));
				document.add(table7);
				document.add(new Chunk(new DottedLineSeparator()));
			}

			// Certificate
			if (!content.getCertificate().getCertificateDescription().equals("")) {
				PdfPTable table8 = new PdfPTable(2);
				table8.setWidthPercentage(100);
				table8.setWidths(new int[] { 7, 17 });
				PdfPCell cell8 = new PdfPCell();
				cell8.addElement(
						new Phrase(new Chunk("CERTIFICATES", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));
				cell8.setBorder(Rectangle.NO_BORDER);
				table8.addCell(cell8);
				table8.addCell(certificate(content.getCertificate()));
				document.add(table8);
				document.add(new Chunk(new DottedLineSeparator()));
			}
			// Publications
			if (!content.getPublication().getPublicationDescription().equals("")) {

				PdfPTable table9 = new PdfPTable(2);
				table9.setWidthPercentage(100);
				table9.setWidths(new int[] { 7, 17 });
				PdfPCell cell9 = new PdfPCell();
				cell9.addElement(
						new Phrase(new Chunk("PUBLICATIONS", new Font(FontFamily.HELVETICA, 14, Font.NORMAL))));
				cell9.setBorder(Rectangle.NO_BORDER);
				table9.addCell(cell9);
				table9.addCell(publications(content.getPublication()));
				document.add(table9);
				document.add(new Chunk(new DottedLineSeparator()));

			}
			document.close();
 		} catch (Exception e) {
			
			logger.error("createPDF method | error occured : " + e.getMessage());
		}
		logger.info("createPDF method | ended. Cv created successfully.");


	}

	private static PdfPCell createImageCell(String path) throws DocumentException, IOException {
		logger.info("createImageCell method | Started.");

		Image img = Image.getInstance(path);

		PdfPCell cell = new PdfPCell(img, true);
		cell.setFixedHeight(125);
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("createImageCell method | ended.");
		return cell;
	}

	private static PdfPCell textOfEntryPart(Personal personal) throws DocumentException, IOException {
		logger.info("textOfEntryPart method | Started.");

		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph(personal.getPersonalName(), new Font(FontFamily.HELVETICA, 22, Font.BOLD));
		Paragraph p2 = new Paragraph(personal.getPersonalTitle(), new Font(FontFamily.HELVETICA, 13, Font.NORMAL));
		Paragraph p3 = new Paragraph(personal.getPersonalObjectives(), new Font(FontFamily.HELVETICA, 14, Font.NORMAL));

		p.setAlignment(Element.ALIGN_MIDDLE);
		p2.setAlignment(Element.ALIGN_MIDDLE);
		p3.setAlignment(Element.ALIGN_LEFT);
		cell.addElement(p);
		cell.addElement(p2);
		cell.addElement(p3);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("textOfEntryPart method | ended.");
		return cell;
	}

	private static PdfPCell personalInfo(Personal personal) throws DocumentException, IOException {
		logger.info("personalInfo method | Started.");

		PdfPCell cell = new PdfPCell();
		String[] titles = new String[] { "Date Of Birth", "Cell Phone", "Office Phone", "E-Mail", "Adress", "Martial Status" };
		String[] contents = new String[] { personal.getPersonalDateofBirth(), personal.getPersonalCellPhone(),
				personal.getPersonalOfficePhone(), personal.getPersonalMail(), personal.getPersonalAddress(), personal.getPersonalMaritalStatus() };

		for (int i = 0; i < titles.length; ++i) {
			if (contents[i]!=null && !contents[i].equals("")) {
				Phrase phrase = new Phrase();
				phrase.add(new Chunk(titles[i] + ": ", new Font(FontFamily.HELVETICA, 13, Font.BOLD)));
				phrase.add(new Chunk(contents[i], new Font(FontFamily.HELVETICA, 13)));

				cell.addElement(phrase);
			}
		}

		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("personalInfo method | ended.");
		return cell;
	}


	private static PdfPCell jopExperience(JobExperience jopEx) throws DocumentException, IOException {
		logger.info("jopExperience method | Started.");

		PdfPCell cell = new PdfPCell();
		for (int i = 0; i < jopEx.getJobCompanyName().size(); ++i) {
			String[] contents = new String[] { jopEx.getJobCompanyName().get(i), jopEx.getJobTitle().get(i),
					jopEx.getJobStartDate().get(i), jopEx.getJobStartDate().get(i), jopEx.getJobDescription().get(i) };
			if (!contents[0].equals("")) {
				Phrase phrase = new Phrase();
				phrase.add(new Chunk(contents[0], new Font(FontFamily.HELVETICA, 14, Font.BOLD)));
				phrase.add(new Chunk("\n" + contents[1] + " " + contents[2] + " - " + contents[3],
						new Font(FontFamily.HELVETICA, 13, Font.ITALIC)));
				phrase.add(new Chunk("\n" + contents[4], new Font(FontFamily.HELVETICA, 13, Font.NORMAL)));
				cell.addElement(phrase);
				cell.addElement(new Paragraph(" "));
			}
		}

		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("jopExperience method | ended.");
		return cell;
	}

	private static PdfPCell education(Education edc) throws DocumentException, IOException {
		logger.info("education method | Started.");

		PdfPCell cell = new PdfPCell();
		for (int i = 0; i < edc.getEduSchoolName().size(); ++i) {

			String[] contents = new String[] { edc.getEduSchoolName().get(i), edc.getEduSchoolDepartman().get(i),
					edc.getEduStartDate().get(i), edc.getEduEndDate().get(i), edc.getEduDescription().get(i) };
			Phrase phrase = new Phrase();
			phrase.add(new Chunk(contents[0], new Font(FontFamily.HELVETICA, 16, Font.BOLD)));
			phrase.add(new Chunk("\n" + contents[1] + " " + contents[2] + " - " + contents[3],
					new Font(FontFamily.HELVETICA, 13, Font.ITALIC)));
			phrase.add(new Chunk("\n" + contents[4], new Font(FontFamily.HELVETICA, 13, Font.NORMAL)));
			cell.addElement(phrase);
			cell.addElement(new Paragraph(" "));
		}
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("education method | ended.");
		return cell;
	}

	private static PdfPCell foreignLanguage(ForeignLanguage fLang) throws DocumentException, IOException {
		logger.info("foreignLanguage method | Started.");

		PdfPCell cell = new PdfPCell();
		for (int i = 0; i < fLang.getForeignName().size(); ++i) {
			String[] titles = new String[] { "Name Of Language", "Language Level" };
			String[] contents = new String[] { fLang.getForeignName().get(i), fLang.getForeignLevel().get(i) };

			Phrase phrase = new Phrase();
			phrase.add(new Chunk(contents[0] + ": ", new Font(FontFamily.HELVETICA, 13, Font.BOLD)));
			phrase.add(new Chunk(contents[1], new Font(FontFamily.HELVETICA, 13, Font.NORMAL)));
			cell.addElement(phrase);
		}
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("foreignLanguage method | ended.");
		return cell;
	}

	private static PdfPCell skills(Skill skill) throws DocumentException, IOException {
		logger.info("skills method | Started.");

		PdfPCell cell = new PdfPCell();
		String temp = skill.getSkillDescription();
		String[] allSkills = temp.split(",");
		Phrase phrase = new Phrase();
		for (int i = 0; i < allSkills.length; ++i) {
			phrase.add(new Chunk("*" + " " + allSkills[i] + "\n", new Font(FontFamily.HELVETICA, 13, Font.BOLD)));
		}

		cell.addElement(phrase);
		cell.addElement(new Paragraph(" "));
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("skills method | ended.");

		return cell;
	}

	private static PdfPCell coursesAndSeminars(Courses courAndSem) throws DocumentException, IOException {
		logger.info("coursesAndSeminars method | Started.");

		PdfPCell cell = new PdfPCell();
		String temp = courAndSem.getCoursesDescription();
		String[] allCourAndSem = temp.split("\\r?\\n");
		Phrase phrase = new Phrase();
		
		for (int i = 0; i < allCourAndSem.length; ++i)
		{
			phrase.add(new Chunk("*" + " " + allCourAndSem[i] + "\n", new Font(FontFamily.HELVETICA, 13, Font.BOLD)));

		}
		cell.addElement(phrase);
		cell.addElement(new Paragraph(" "));
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("coursesAndSeminars method | ended.");
		return cell;
	}

	private static PdfPCell certificate(Certificate certificate) throws DocumentException, IOException {
		logger.info("certificate method | Started.");

		PdfPCell cell = new PdfPCell();
		String temp = certificate.getCertificateDescription();
		String[] allCertificate = temp.split("\\r?\\n");
		Phrase phrase = new Phrase();
		for (int i = 0; i < allCertificate.length; ++i)
		{
			phrase.add(new Chunk("*" + " " + allCertificate[i] + "\n", new Font(FontFamily.HELVETICA, 13, Font.BOLD)));
		}
		cell.addElement(phrase);
		cell.addElement(new Paragraph(" "));
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("certificate method | ended.");
		return cell;

	}

	private static PdfPCell publications(Publication publication) throws DocumentException, IOException {
		logger.info("publications method | Started.");

		PdfPCell cell = new PdfPCell();
		String temp = publication.getPublicationDescription();
		String[] allPublication = temp.split("\\r?\\n");
		Phrase phrase = new Phrase();
		for (int i = 0; i < allPublication.length; ++i) {
			if (!allPublication[i].equals(""))
				phrase.add(new Chunk("* " + allPublication[i] + "\n", new Font(FontFamily.HELVETICA, 13, Font.BOLD)));
		}
		cell.addElement(phrase);
		cell.addElement(new Paragraph(" "));
		cell.setBorder(Rectangle.NO_BORDER);
		
		logger.info("publications method | ended.");
		return cell;
	}
}
