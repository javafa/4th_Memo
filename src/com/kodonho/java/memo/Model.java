package com.kodonho.java.memo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//�����͸� �����ϴ� ����Ҹ� �����ϴ� ��ü
public class Model {
	private final String DB_DIR  = "c:/workspaces/java/database";
	private final String DB_FILE = "memo.txt";
	//                     mac ��       "/workspaces/java/database"
	private File database = null;
	
	// ��ü �޸� �����ϴ� �����
	ArrayList<Memo> list = new ArrayList<>();
	// ������ �۹�ȣ
	int lastIndex = 0;
	
	public Model(){
		// new �ϴ� ���� �� ������ ����ȴ�.
		File dir = new File(DB_DIR);
		// ���丮�� ��������
		if(!dir.exists()){
			dir.mkdirs(); // ��λ� ���丮�� ������ �ڵ�����
		}
		// window     = \
		// mac        = /
		// unix,linux = /
		File file = new File(DB_DIR + File.separator + DB_FILE);
		// ������ ��������
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
	
	// ����
	public void create(Memo memo){
		//�۹�ȣ
		memo.no = ++lastIndex;
		// memo ��ü�� ������ ���Ͽ� ����
		System.out.println(database.getPath());
		try(FileOutputStream fos = new FileOutputStream(database, true)) {
			// ������ ������ �����ڷ� �и��Ͽ� ������ ���ڿ��� �ٲ۴�.
			String row = memo.no + COLUMN_SEP 
					+ memo.name + COLUMN_SEP 
					+ memo.content + COLUMN_SEP 
					+ memo.datetime + "\n";
			System.out.println("����|"+row);
			// 1. ���� ��Ʈ���� ����
			
			// 2. ��Ʈ���� �߰�ó��... (�ؽ�Ʈ�� ���ڵ��� ����...)
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			// 3. ����ó��...
			BufferedWriter bw = new BufferedWriter(osw);
			bw.append(row);
			bw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// �� �ϳ��� ������ �޸𸮸� ����ҷ� �̵�
		list.add(memo);
		
	}
	// �б�
	public Memo read(int no){
		for(Memo memo : list){
			if(memo.no == no){
				return memo;
			}
		}
		return null;
	}
	// ����
	public void update(Memo memo){
		
	}
	// ����
	public void delete(int no){
		for(Memo memo : list){
			if(memo.no == no){
				list.remove(memo);
			}
		}
	}
	// ���
	public ArrayList<Memo> getList(){
		return list;
	}
}