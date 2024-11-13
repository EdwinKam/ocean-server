package com.ocean.whale.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private final Firestore firestore;

    @Autowired
    public FirestoreService(Firestore firestore) {
        this.firestore = firestore;
    }

    public String addDocument(String collectionName, String documentId, Map<String, Object> data) throws ExecutionException, InterruptedException {
        CollectionReference collection = firestore.collection(collectionName);
        ApiFuture<WriteResult> future = collection.document(documentId).set(data);
        return future.get().getUpdateTime().toString();
    }

    public Map<String, Object> getDocument(String collectionName, String documentId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document(documentId);
        return docRef.get().get().getData();
    }

    public boolean deleteDocument(String collectionName, String documentId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document(documentId);
        ApiFuture<WriteResult> writeResult = docRef.delete();
        // Optionally, you can check the result of the delete operation
        return writeResult.get() != null;
    }
}