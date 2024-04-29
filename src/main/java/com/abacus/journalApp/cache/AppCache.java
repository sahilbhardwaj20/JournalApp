package com.abacus.journalApp.cache;

import com.abacus.journalApp.entity.ConfigEntity;
import com.abacus.journalApp.repository.ConfigRepo;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    ConfigRepo configRepo;

    private Map<String, String> cacheMap = new HashMap<>();

    @PostConstruct
    public void init(){
        cacheMap = new HashMap<>();
        List<ConfigEntity> allConfigs = configRepo.findAll();
        for (ConfigEntity configEntity : allConfigs)
            cacheMap.put(configEntity.getKey(),configEntity.getValue());
    }

}
