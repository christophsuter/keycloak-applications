package ch.noser.oauth2.rest.api.internal;

import io.dropwizard.servlets.assets.AssetServlet;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class FileSystemServlet extends AssetServlet {

   private static final long serialVersionUID = -8919831400410572728L;

   public FileSystemServlet(String resourcePath, String uriPath, String indexFile, Charset defaultCharset) {
      super(resourcePath, uriPath, indexFile, defaultCharset);
   }

   @Override
   protected URL getResourceUrl(String absoluteRequestedResourcePath) {
      File f = new File(absoluteRequestedResourcePath);
      if(!f.exists()) {
         throw new IllegalArgumentException("\"" + absoluteRequestedResourcePath + "\" not found.");
      }
      try {
         return f.toURI().toURL();
      } catch (MalformedURLException ex) {
         throw new IllegalArgumentException(ex);
      }
   }

   @Override
   protected byte[] readResource(URL requestedResourceURL) throws IOException {
      File file = new File(requestedResourceURL.getFile());
      FileInputStream fis = new FileInputStream(file);
      return IOUtils.toByteArray(fis);
   }

}
