package info.hernanramirez.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.validation.TypeInfoProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hernanramirez.tipcalc.R;
import info.hernanramirez.tipcalc.activities.TipDetailActivity;
import info.hernanramirez.tipcalc.adapters.OnItemClickListener;
import info.hernanramirez.tipcalc.adapters.TipAdaper;
import info.hernanramirez.tipcalc.models.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private TipAdaper adapter;


    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();

        return view;
    }

    private void initAdapter() {
        if (adapter == null){
            adapter = new TipAdaper(getActivity().getApplicationContext(), this);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);

    }

    @Override
    public void clearList() {
        adapter.clear();

    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, tipRecord.getBill());
        intent.putExtra(TipDetailActivity.DATE_KEY, tipRecord.getDateFormatted());
        startActivity(intent);
    }
}
