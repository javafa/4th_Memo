package com.kodonho.java.memo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//데이터를 저장하는 저장소를 관리하는 객체
public class Model {
	private final String DB_DIR  = "c:/workspaces/java/database";
	private final String DB_FILE = "memo.txt";
	//                     mac 은       "/workspaces/java/database"
	private File database = null;
	
	// 전체 메모를 저장하는 저장소
	ArrayList<Memo> list = new ArrayList<>();
	// 마지막 글번호
	int lastIndex = 0;
	
	public Model(){
		// new 하는 순간 이 영역이 실행된다.
		File dir = new File(DB_DIR);
		// 디렉토리의 존재유무
		if(!dir.exists()){
			dir.mkdirs(); // 경로상에 디렉토리가 없으면 자동생성
		}
		// window     = \
		// mac        = /
		// unix,linux = /
		File file = new File(DB_DIR + File.separator + DB_FILE);
		// 파일의 존재유무
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		database = file;
	}
	
	private final String COLUMN_SEP = "::";
	
	// 생성
	public void create(Memo memo){
		//글번호
		memo.no = ++lastIndex;
		// memo 객체의 내용을 파일에 쓴다
		System.out.println(database.getPath());
		try(FileOutputStream fos = new FileOutputStream(database, true)) {
			// 저장할 내용을 구분자로 분리하여 한줄의 문자열로 바꾼다.
			String row = memo.no + COLUMN_SEP 
					+ memo.name + COLUMN_SEP 
					+ memo.content + COLUMN_SEP 
					+ memo.datetime + "\n";
			System.out.println("한줄|"+row);
			// 1. 쓰는 스트림을 연다
			
			// 2. 스트림을 중간처리... (텍스트의 엔코딩을 변경...)
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			// 3. 버퍼처리...
			BufferedWriter bw = new BufferedWriter(osw);
			bw.append(row);
			bw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 글 하나를 저장한 메모리를 저장소로 이동
		list.add(memo);
		
	}
	// 읽기
	public Memo read(int no){
		for(Memo memo : list){
			if(memo.no == no){
				return memo;
			}
		}
		return null;
	}
	// 수정
	public void update(Memo memo){
		
	}
	// 삭제
	public void delete(int no){
		for(Memo memo : list){
			if(memo.no == no){
				list.remove(memo);
			}
		}
	}
	// 목록
	public ArrayList<Memo> getList(){
		return list;
	}
}