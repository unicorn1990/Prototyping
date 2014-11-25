package com.xingfu360.prototyping;

import java.io.File;

import com.xingfu360.constant.Constants;
import com.xingfu360.wideget.util.WidgetFactory;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		File folder = new File(Constants.AppRootDir);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		writeSample2SD();
	}

	/**
	 * 写初始数据到sd卡  ,未完待续
	 */
	private void writeSample2SD() {
		
	}

	@Override
	public void onNavigationDrawerItemSelected(int position, String text) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1, text))
				.commit();
	}

	public void onSectionAttached(int number, String text) {
		mTitle = number + "." + text;
		/*
		 * switch (number) { case 1: mTitle =
		 * getString(R.string.title_section1); break; case 2: mTitle =
		 * getString(R.string.title_section2); break; case 3: mTitle =
		 * getString(R.string.title_section3); break; }
		 */
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private static final String ARG_SECTION_TEXT = "section_text";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber,
				String sectionText) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			args.putString(ARG_SECTION_TEXT, sectionText);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		GridAdapter adapter;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final RelativeLayout rootView = (RelativeLayout) inflater.inflate(
					R.layout.fragment_main, container, false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER))
					+ getArguments().getString(ARG_SECTION_TEXT));

			Button btn_generate=(Button) rootView.findViewById(R.id.btn_generate);
			btn_generate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					WidgetFactory factory = WidgetFactory.getInstance();
					factory.setDemoName(getArguments().getString(ARG_SECTION_TEXT));

					Intent intent = new Intent(getActivity(),
							EmptyActivity.class);
					intent.putExtra(EmptyActivity.XML_FNAME, "main_cn.xml");
					startActivity(intent);
				
				}
			});
			
			if (adapter == null)
				adapter = new GridAdapter(getActivity());
			adapter.setDemoName(getArguments().getString(ARG_SECTION_TEXT));

			GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
			gridView.setAdapter(adapter);
			gridView.setOnItemClickListener(adapter);
			
			return rootView;
		}

		

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(
					getArguments().getInt(ARG_SECTION_NUMBER), getArguments()
							.getString(ARG_SECTION_TEXT));
		}
	}

}
