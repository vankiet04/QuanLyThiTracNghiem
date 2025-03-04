package BUS;

import DAO.DAO_Log;
import DTO.DTO_Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BUS_Log {
   public DAO_Log logDAO = new DAO_Log();


    public BUS_Log() {
        
    }
    
    public int insert(DTO_Log log){
        log = LayLogContentMoiNhat(log);
        return logDAO.insert(log);
        
    }
    
    public DTO_Log LayLogContentMoiNhat(DTO_Log log){
        String lastContent = logDAO.LayLogContentMoiNhat(log.getLogUserID(), log.getLogExCode());
        log.setLogContent(updateLogContent(lastContent, log.getLogContent()));
        return log;
    }
    
    
    public ArrayList<DTO_Log> LayLogCuaNguoiThi(int userID, String exCode, int time) {
        ArrayList<DTO_Log> res = logDAO.LayLogCuaNguoiThiTheoThoiGian(userID, exCode, time);
        // if rỗng thì lần thi trước đã done
        if(res.isEmpty()){
            return new ArrayList<>();
        }
        return res;
    }

   
    private String updateLogContent(String lastContent, String curContent) {
        if (lastContent == null || lastContent.isEmpty())
            return curContent;

        Map<String, String> logMap = new LinkedHashMap<>();
        String[] entries = lastContent.split(", ");
        for (String entry : entries) {
            String[] parts = entry.split(":");
            if (parts.length == 2)
                logMap.put(parts[0], parts[1]);
        }

        // Thay thế hoặc thêm mới lựa chọn
        String[] newParts = curContent.split(":");
        logMap.put(newParts[0], newParts[1]);

        // Ghép lại thành chuỗi logContent mới
        return logMap.entrySet().stream()
                     .map(entry -> entry.getKey() + ":" + entry.getValue())
                     .collect(Collectors.joining(", "));
    }

    
}
