package info.hernanramirez.tipcalc.fragments;


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
import info.hernanramirez.tipcalc.adapters.TipAdaper;
import info.hernanramirez.tipcalc.models.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener {

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
            adapter = new TipAdaper(getActivity().getApplicationContext(), new ArrayList<TipRecord>());
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void addToList(TipRecord record) {

    }

    @Override
    public void clearList() {

    }
}
