package org.jingfu.paperbrowser;

import java.util.List;

import org.jingfu.paperbrowser.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

public class PaperBrowserActivity extends Activity {

	private static final String TAG = "PaperBrowserActivity";
	public final static String EXTRA_MESSAGE = "message";

	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private Spinner semesterSpinner;
	private LinearLayout drawerLienear;
	private ActionBarDrawerToggle drawerToggle;
	private ListView paperList;
	private List<Paper> papers;
	private ArrayAdapter<Paper> paperListAdapter;

	private CharSequence drawerTitle;
	private CharSequence title;
	private String[] subjects;
	private String[] levels;

	private PaperService paperService = PaperService.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		setContentView(R.layout.activity_paper_browser);
		setUpActionBar();
		setUpNavigationDrawer();
		initPaperListView();
		handleIntent(getIntent());
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			paperService.filterPapersByCodeOrName(query);
			paperListAdapter.notifyDataSetChanged();
		}

	}

	private void initPaperListView() {
		papers = paperService.getAllPapers();
		paperList = (ListView) findViewById(R.id.paper_list);
		paperListAdapter = new ArrayAdapter<Paper>(this,
				R.layout.list_view_item, papers);
		paperList.setAdapter(paperListAdapter);
		paperList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				displayPaper(parent, position);

			}

			private void displayPaper(AdapterView<?> parent, int position) {
				Paper paper = (Paper) parent.getAdapter().getItem(position);
				Intent intent = new Intent(getApplicationContext(),
						PaperInfoActivity.class);
				String title = paper.toString();
				intent.putExtra(EXTRA_MESSAGE, title);
				startActivity(intent);
			}
		});

	}

	private void setUpActionBar() {
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		levels = getResources().getStringArray(R.array.level_list);

		ArrayAdapter<String> level_list_adapter = new ArrayAdapter<String>(
				actionBar.getThemedContext(),
				android.R.layout.simple_list_item_1, android.R.id.text1, levels);
		actionBar.setListNavigationCallbacks(level_list_adapter,
				new NavigationListerner());
	}

	private void setUpNavigationDrawer() {
		title = drawerTitle = getTitle();
		subjects = getResources().getStringArray(R.array.subject_list);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLienear = (LinearLayout) findViewById(R.id.left_drawer);
		drawerList = (ListView) findViewById(R.id.left_menu);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		drawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, R.id.drawer_item_label, subjects));
		drawerList.setOnItemClickListener(new DrawerItemClickListener());

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(title);
				getActionBar().show();
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(drawerTitle);
				getActionBar().hide();
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);

	}

	private class NavigationListerner implements ActionBar.OnNavigationListener {

		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			String[] levels = getResources().getStringArray(R.array.level_list);
			String level = levels[itemPosition];
			String subject = getSelectedSubject();
			String semester = semesterSpinner.getSelectedItem().toString();
			updatePaperListView(subject, level, semester);
			return true;
		}

	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			drawerList.setItemChecked(position, true);
			String subject = subjects[position];
			setTitle(paperService.getSubjectAbbr(subject));
			String level = getSelectedLevel();
			String semester = semesterSpinner.getSelectedItem().toString();
			updatePaperListView(subject, level, semester);
			drawerLayout.closeDrawer(drawerLienear);
		}
	}

	private void updatePaperListView(String subject, String level,
			String semester) {
		paperService.filterPapers(subject, level, semester);
		paperListAdapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paper_browser, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		MenuItem searchMenuItem = (MenuItem) menu.findItem(R.id.action_search);
		final Menu activityMenu = menu;
		searchMenuItem
				.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

					@Override
					public boolean onMenuItemActionExpand(MenuItem item) {
						activityMenu.findItem(R.id.action_filter_semester)
								.setVisible(false);
						activityMenu.findItem(R.id.action_settings).setVisible(
								false);
						drawerLayout
								.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
						paperListAdapter.clear();
						paperListAdapter.notifyDataSetChanged();
						return true;
					}

					@Override
					public boolean onMenuItemActionCollapse(MenuItem item) {
						activityMenu.findItem(R.id.action_filter_semester)
								.setVisible(true);
						activityMenu.findItem(R.id.action_settings).setVisible(
								true);
						drawerLayout
								.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
						String subject = getSelectedSubject();
						String level = getSelectedLevel();
						String semester = semesterSpinner.getSelectedItem()
								.toString();
						paperService.filterPapers(subject, level, semester);
						return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
					}
				});
		
		SearchView searchView = (SearchView) searchMenuItem.getActionView();
		searchView.setIconifiedByDefault(true);
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		semesterSpinner = (Spinner) menu.findItem(R.id.action_filter_semester)
				.getActionView();
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.semester_list, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		semesterSpinner.setAdapter(adapter);
		semesterSpinner
				.setOnItemSelectedListener(new SpinnerItemSelectedListener());
		return true;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		handleIntent(intent);
	}

	private class SpinnerItemSelectedListener implements
			AdapterView.OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String semester = (String) parent.getAdapter().getItem(position);
			String subject = getSelectedSubject();
			String level = getSelectedLevel();
			updatePaperListView(subject, level, semester);
		}

		@Override
		public void onNothingSelected(AdapterView<?> view) {

		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here.
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "Settings action", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title) {
		this.title = title;
		getActionBar().setTitle(title);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		Log.d(TAG, "onPostCreate()");
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		drawerToggle.onConfigurationChanged(newConfig);
	}

	private String getSelectedSubject() {
		String subject;
		int index = drawerList.getCheckedItemPosition();
		if (index >= 0) {
			subject = subjects[index];
		} else {
			subject = PaperService.ALL_SUBJECT;
		}
		return subject;
	}

	private String getSelectedLevel() {
		String level;
		int index = getActionBar().getSelectedNavigationIndex();
		if (index >= 0) {
			level = levels[index];
		} else {
			level = PaperService.ALL_LEVEL;
		}
		return level;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart()");

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

}
