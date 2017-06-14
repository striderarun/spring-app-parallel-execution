package com.dashboard.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ParallelProcessor {

    public static <T> List<T> execute(List<Supplier<T>> suppliers) {
    	ExecutorService executor = Executors.newCachedThreadPool();
    	List<CompletableFuture<T>> futures = new ArrayList<>();
        try {
        	for (Supplier<T> supplier: suppliers) {
        		futures.add(CompletableFuture.supplyAsync(supplier, executor));
        	}
        	return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        } finally {
            executor.shutdown();
        }
    }

    public static <T> List<T> genericParallelExecutor(Map<Object, List<Signature>> inputMap) {
		List<Supplier<T>> suppliersList = new ArrayList<>();
    	try {
			for(Entry<Object, List<Signature>> entry: inputMap.entrySet()) {
				for(Signature signature: entry.getValue()) {
					suppliersList.add(SupplierFactory.createGenericReflectionSupplier(entry.getKey(), signature.getName(), signature.getReturnType(), signature));
				}
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return execute(suppliersList);
    }
}
