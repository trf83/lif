package br.com.controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.Part;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoaderListener;

import br.com.interfaces.ILIFController;
import br.com.lif.util.GsonUtils;
import br.com.to.Pessoa;

@Controller("controlador")
@Scope("request")
public class Controlador implements ILIFController {

	private static final long serialVersionUID = -2482521909785011989L;

	public String recuperar(Pessoa pessoa) {
		return GsonUtils.getInstance().objectToJson(pessoa);
	}

	public void upload(Collection<Part> files) throws IOException {
		final String path = ContextLoaderListener.getCurrentWebApplicationContext()
												 .getServletContext()
												 .getRealPath("arquivos");
		for (Part filePart : files) {
			String fileName = getFileName(filePart);
			OutputStream out = null;
			InputStream filecontent = null;

			try {
				File arquivo = new File(path + File.separator + fileName);
				out = new FileOutputStream(arquivo);
				filecontent = filePart.getInputStream();
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
			}
		}
	}

	private String getFileName(final Part part) {
		String retorno = null;
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				retorno = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return retorno;
	}
}
