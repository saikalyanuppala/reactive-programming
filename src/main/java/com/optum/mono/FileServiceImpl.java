package com.optum.mono;

import com.optum.util.Util;

public class FileServiceImpl {
	public static void main(String[] args) {
		FileService.read("file1.txt").subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		FileService.write("file3.txt", "This is file 3, written in a program1111").subscribe(Util.onNext());

		FileService.delete("file3.txt").subscribe(Util.onNext(), Util.onError(), Util.onComplete());
	}
}
