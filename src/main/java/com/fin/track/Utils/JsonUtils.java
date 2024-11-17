package com.fin.track.Utils;

import com.fin.track.Controllers.Client.Transaction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TRANSACTIONS_FILE = "transactions.json";
    private static final Gson gson = new Gson();

    public static List<Transaction> loadTransactions() {
        try (FileReader reader = new FileReader(TRANSACTIONS_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Transaction>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
} 