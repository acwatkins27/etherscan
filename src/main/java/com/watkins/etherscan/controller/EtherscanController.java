package com.watkins.etherscan.controller;

import com.watkins.etherscan.client.EtherscanAccountClient;
import com.watkins.etherscan.model.DownloadJson;
import com.watkins.etherscan.model.QueryParameters;
import com.watkins.etherscan.util.WorkbookUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Log4j2
public class EtherscanController {

    private final EtherscanAccountClient transactionClient;

    private final WorkbookUtil workbookUtil = new WorkbookUtil();

    @GetMapping("/query")
    public String createQuery(Model model) {
        model.addAttribute("parameters", new QueryParameters());
        return "query";
    }

    @PostMapping("/query")
    public String queryResult(@ModelAttribute QueryParameters parameters, Model model) {
        JSONObject response = transactionClient.getBalance(parameters);
        model.addAttribute("download", new DownloadJson());
        model.addAttribute("response", response.toString());
        if (response.get("result") instanceof String) {
            return "text_result";
        } else {
            List<List<String>> listOfResults = new ArrayList<>();
            JSONArray results = response.getJSONArray("result");
            WorkbookUtil.getListOfResults(results, listOfResults);
            Set<String> keySet = new JSONObject(results.get(0).toString()).keySet();
            model.addAttribute("keys", keySet);
            model.addAttribute("listOfResults", listOfResults);
            return "table_result";
        }
    }

    @PostMapping("/query/download")
    @ResponseBody
    public void downloadFile(@ModelAttribute DownloadJson download, HttpServletResponse response) throws IOException {
        Workbook workbook = workbookUtil.writeObjects2ExcelFile(new JSONObject(download.getBody()));
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = result.xlsx";
        response.setContentType("application/octet-stream");
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader(headerKey, headerValue);
        outputStream.write(workbookUtil.getEmptyExcelFileAsBytes(workbook));
        outputStream.close();
    }
}
