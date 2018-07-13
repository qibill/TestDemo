package net.biosan.net.service;

import java.util.List;

import net.biosan.net.ImportBean;
import net.marginland.mland.common.utils.file.FileUtil;

public interface ReadFileService {

	abstract List<ImportBean> readFile(String dir, FileUtil fileUtil, String encode, String must);
}
