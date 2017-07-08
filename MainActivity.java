package syaiful.finalpro.englishcourse.uitry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.Recycler.MyAdapter;
import syaiful.finalpro.englishcourse.ui.Detail;

/**
 * Created by syaiful9508 on 04/07/17.
 */

public class MainActivity extends Fragment{

    Context mActivity;

    private RecyclerView recyclerview;
    private com.android.volley.RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;

    AdapterList adapter;

    public MainActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card, container, false);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplication());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));

        //recyclerview.setLayoutManager(llm);

        final Config config = new Config();

        //07-07-2017



        requestQueue    = Volley.newRequestQueue(getActivity().getApplicationContext());
        list_data   = new ArrayList<HashMap<String, String>>();
        stringRequest   = new StringRequest(Request.Method.GET, config.URL_SUBJECT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: ", response);
                try {
                    //JSONObject jsonObject = new JSONObject(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray(response);
                    JSONArray jsonArray = new JSONArray(response);
                    for (int a = 0; a < jsonArray.length(); a++) {
                        //JSONObject json = jsonArray.getJSONObject(a);
                        JSONObject json = jsonArray.getJSONObject(a);
                        String id = json.getString(config.TAG_ID);
                        String subjects = json.getString(config.TAG_SUBJECTS);
                        final String content = json.getString(config.TAG_CONTENT);
                        String img      = json.getString(config.TAG_IMAGE);
                        final HashMap<String, String> map = new HashMap<String, String>();

                        map.put(config.TAG_ID, id);
                        map.put(config.TAG_SUBJECTS, subjects);
                        map.put(config.TAG_CONTENT, content);
                        map.put(config.TAG_IMAGE, img);

                        /*map.put("id", json.getString("id"));
                        map.put("subjects", json.getString("subjects"));
                        map.put("content", json.getString("content"));*/

                        list_data.add(map);
                        adapter = new AdapterList(getActivity(), list_data, new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //HashMap<String , String > map1 = list_data.get(position);
                                Intent in = new Intent(getActivity(), Detail.class);
                               // in.putExtra(config.TAG_ID, map.get(config.TAG_ID).toString());
                                ArrayList<HashMap<String ,String >> listID = new ArrayList<HashMap<String, String>>();
                                //String id = listID.get(position).get(config.TAG_ID);
                                in.putExtra(config.TAG_ID, map.get(config.TAG_ID));



                                startActivity(in);


                            }
                        });
                        recyclerview.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);

        return view;
    }
}
