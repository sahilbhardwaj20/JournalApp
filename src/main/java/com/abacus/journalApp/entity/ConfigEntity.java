package com.abacus.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "config_journal_app")
public class ConfigEntity {

    @Indexed(unique = true)
    @NonNull
    private String key;
    @NonNull
    private String value;
}