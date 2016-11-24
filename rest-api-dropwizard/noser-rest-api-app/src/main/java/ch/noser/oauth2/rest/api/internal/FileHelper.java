package ch.noser.oauth2.rest.api.internal;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

public class FileHelper {

   private static final List<String> UPDATEABLE_FILES = Lists.newArrayList("html", "json", "js", "css");
   private static final Logger LOG = LoggerFactory.getLogger(FileHelper.class);

   public void deleteFolder(String folder) throws IOException {
      File processed = new File(folder);
      if (processed.exists()) {
         FileUtils.deleteDirectory(processed);
      }
   }

   public void copyFiles(String originalFolder, String newFolder) throws IOException {
      File original = new File(originalFolder);
      File processed = new File(newFolder);

      LOG.info("copyFiles: {} -> {}", original.getAbsolutePath(), processed.getAbsolutePath());

      if (!processed.mkdirs()) {
         throw new IOException("Could not genereate directory: " + processed.getAbsolutePath());
      }

      FileUtils.copyDirectory(original, processed);
   }

   public void replacePlaceholder(String folder, List<Placeholder> placeholders) throws IOException {
      File processing = new File(folder);
      LOG.info("replacePlaceholder in files: {}", processing.getAbsolutePath());
      Collection<File> files = FileUtils.listFiles(processing, new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);

      for (File processingFile : files) {
         String extension = FilenameUtils.getExtension(processingFile.getAbsolutePath()).toLowerCase();
         if (UPDATEABLE_FILES.contains(extension)) {
            replacePlaceholderInFile(processingFile, placeholders);
         }
      }
   }

   private void replacePlaceholderInFile(File f, List<Placeholder> placeholders) throws IOException {
      String content = IOUtils.toString(new FileInputStream(f), StandardCharsets.UTF_8);
      content = replacePlaceHolder(content, placeholders);
      IOUtils.write(content, new FileOutputStream(f), StandardCharsets.UTF_8);
   }

   private String replacePlaceHolder(String line, List<Placeholder> placeholders) {
      for (Placeholder p : placeholders) {
         line = line.replace(p.getKey(), p.getValue());
      }
      return line;
   }

}
