package com.mrwu.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mrwu.demo.DemoAdapter.ViewHolder;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	private ViewGroup btnCancle = null;

	private ViewGroup btnAdd = null;

	private Button btnSelectAll = null;

	private Button btnDelete = null;

	private ListView lvListView = null;

	private DemoAdapter adpAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		initData();

	}

	private void initView() {

		btnCancle = (ViewGroup) findViewById(R.id.btnCancle);
		btnCancle.setOnClickListener(this);

		btnAdd = (ViewGroup) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);

		btnSelectAll = (Button) findViewById(R.id.btnSelectAll);
		btnSelectAll.setOnClickListener(this);

		lvListView = (ListView) findViewById(R.id.lvListView);
		lvListView.setOnItemClickListener(this);

	}

	private void initData() {

		List<DemoBean> demoDatas = new ArrayList<DemoBean>();

		adpAdapter = new DemoAdapter(this, demoDatas);

		lvListView.setAdapter(adpAdapter);

	}

	@Override
	public void onClick(View v) {

		if (v == btnCancle) {
			finish();

		}
		
		EditText tv1=(EditText) findViewById(R.id.ntodo);
		
		if (v == btnAdd) {

			String newTodo =tv1.getText().toString();
			adpAdapter.add(new DemoBean(newTodo, true));

			adpAdapter.notifyDataSetChanged();
		}

		if (v == btnDelete) {


			Map<Integer, Boolean> map = adpAdapter.getCheckMap();

			int count = adpAdapter.getCount();

			for (int i = 0; i < count; i++) {

				int position = i - (count - adpAdapter.getCount());

				if (map.get(i) != null && map.get(i)) {

					DemoBean bean = (DemoBean) adpAdapter.getItem(position);

					if (bean.isCanRemove()) {
						adpAdapter.getCheckMap().remove(i);
						adpAdapter.remove(position);
					} else {
						map.put(position, false);
					}

				}
			}

			adpAdapter.notifyDataSetChanged();

		}

		if (v == btnSelectAll) {

			if (btnSelectAll.getText().toString().trim().equals("全选")) {

				adpAdapter.configCheckMap(true);

				adpAdapter.notifyDataSetChanged();

				btnSelectAll.setText("全不选");
			} else {

				adpAdapter.configCheckMap(false);

				adpAdapter.notifyDataSetChanged();

				btnSelectAll.setText("全选");
			}

		}
	}


	@Override
	public void onItemClick(AdapterView<?> listView, View itemLayout,
			int position, long id) {

		if (itemLayout.getTag() instanceof ViewHolder) {

			ViewHolder holder = (ViewHolder) itemLayout.getTag();

			holder.cbCheck.toggle();

		}

	}
}
