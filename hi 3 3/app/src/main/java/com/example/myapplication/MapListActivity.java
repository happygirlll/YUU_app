    package com.example.myapplication;

    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.inputmethod.EditorInfo;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.SearchView;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.ArrayList;
    import java.util.List;

    public class MapListActivity extends AppCompatActivity {
        private static final int REQUEST_CODE_SELECT_BUILDING = 1;
        private BuildingDataAdapter mAdapter;
        public static List<BuildingData> mapDataList; // 변경된 부분
        private RecyclerView recyclerView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maplist);
            mapDataList = MapDataUtil.initializeMapDataList();
            setUpRecyclerView();
            getSupportActionBar().setTitle("상세정보");
        }
        private void setUpRecyclerView() {
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new BuildingDataAdapter(mapDataList,this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.search_menu, menu);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String s) {
                    mAdapter.getFilter().filter(s);
                    return false; }
            });
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle presses on the action bar items
            int id = item.getItemId();
            if (id == R.id.back_btn) { //map
                Intent intent = new Intent(this, SchoolMapActivity.class);
                startActivity(intent);
            }
            return true;
        }


    }