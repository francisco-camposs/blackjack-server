package br.ufrn.imd.blackjackServer.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutInput {
	
	public static Long DEFAULT_TIMEOUT = 15l;

	static public String string() {
		ExecutorService ex = Executors.newSingleThreadExecutor();
		Future<String> request = ex.submit(new StringInput());
		try {
			String name = request.get(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
			return name;
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			return "";
		}
	}

}


class StringInput implements Callable<String> {

	@Override
	public String call() throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		do {
		      try {
		        while (!br.ready()) {
		          Thread.sleep(TimeoutInput.DEFAULT_TIMEOUT);
		        }
		        input = br.readLine();
		      } catch (InterruptedException e) {
		        return "";
		      }
		    } while ("".equals(input));
		return input;
	}

}
