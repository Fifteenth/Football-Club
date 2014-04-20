package com.android.activity;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.android.activity.FinanceNoticeActivity.DetailEntity;
import com.android.adapter.FinanceRecordAdapter;
import com.android.base.ConstantVariable;
import com.android.base.util.FileUtil;
import com.android.base.util.SDCardUtil;
import com.android.base.variable.FileVariable;
import com.android.club.R;
import com.android.service.FinanceService;
import com.android.to.FinanceTO;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FinanceRecordActivity extends Activity {

	public static String playerName;
	
	List<FinanceTO> financePaymentList = new ArrayList<FinanceTO>();
	ListView listview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_finance_record);

		List <FinanceTO> financePaymentList= getFinanceTOList();
		if(financePaymentList!=null
				&&financePaymentList.size()!=0){
			listview = (ListView) this.findViewById(R.id.listView_my);

			// 实例化自定义适配器
			FinanceRecordAdapter adapter = new FinanceRecordAdapter(this, financePaymentList);

			listview.setAdapter(adapter);

			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// Toast.makeText(TeamActivity.this, "选中的是:" + position,
					// Toast.LENGTH_SHORT).show();

					listview.setItemChecked(position, true);
				}
			});
		}
	}

	public List <FinanceTO> getFinanceTOList(){
		String sdCardRootPath = SDCardUtil.getRootPath();
		InputStream inputStreamFinance = FileUtil.getFileInputStream(
				new File(sdCardRootPath,FileVariable.FINANCE_PAYMENT));
		List <FinanceTO> financeTOList = new ArrayList<FinanceTO>();;
		if(inputStreamFinance!=null){
			financeTOList = FinanceService.getFinanceTOList(inputStreamFinance,playerName);
		}
		inputStreamFinance = FileUtil.getFileInputStream(
				new File(sdCardRootPath,FileVariable.FINANCE_DEDUCTION));
		if(inputStreamFinance!=null){
			financeTOList.addAll(FinanceService.getFinanceTOList(inputStreamFinance,playerName));
		}
		
		return financeTOList;
	}
}
