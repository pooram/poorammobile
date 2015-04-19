package com.pooram.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.pooram.library.foldablelayout.UnfoldableView;
import com.pooram.library.foldablelayout.commons.utils.Views;
import com.pooram.library.foldablelayout.items.Painting;
import com.pooram.library.foldablelayout.items.PaintingsAdapter;
import com.pooram.library.foldablelayout.shading.GlanceFoldShading;
import com.pooram.library.slider.SliderLayout;
import com.pooram.library.slider.SliderTypes.BaseSliderView;
import com.squareup.picasso.Picasso;

public class UnfoldableDetailsActivity extends BaseActivity  implements
BaseSliderView.OnSliderClickListener{

	//left menu
	private String[] mPlanetTitles = new String[]{"item1","item2"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 	private DrawerArrowDrawable drawerArrow;
	
    private ListView mListView;
    private View mListTouchInterceptor;
    private View mDetailsLayout;
    private UnfoldableView mUnfoldableView;
    ArrayList<String> imageUrls = new ArrayList<String>();
	ArrayList<String> imageTitles = new ArrayList<String>();
	ArrayList<Integer> articleIds = new ArrayList<Integer>();
	ArrayList<Painting> paintings = new ArrayList<Painting>();
	private SliderLayout eventImageSlider = null;
	private SliderLayout eventElephantSlider = null;
	private SliderLayout eventArticleSlider = null;
	private SliderLayout eventVenueSlider = null;
	private SliderLayout eventMelamSlider = null;
	private View frmLayout = null;
	private LinearLayout detail = null;
	private int isActionMenuOpened = 0;
	List<Painting> eventImagesArray = new ArrayList<Painting>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfoldable_details);
        
        //ad mob code
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        mListView = Views.find(this, R.id.list_view);
//        mListView.setAdapter(new PaintingsAdapter(this));
        
     // drawer
 		ActionBar ab = getActionBar();
 		ab.setDisplayHomeAsUpEnabled(true);
 		ab.setHomeButtonEnabled(true);

 		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
 		mDrawerList = (ListView) findViewById(R.id.navdrawer);

 		drawerArrow = new DrawerArrowDrawable(this) {
 			@Override
 			public boolean isLayoutRtl() {
 				return false;
 			}
 		};
 		
 		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
 				drawerArrow, R.string.drawer_open, R.string.drawer_close) {

 			public void onDrawerClosed(View view) {
 				super.onDrawerClosed(view);
 				isActionMenuOpened = 0;
 				invalidateOptionsMenu();
 			}

 			public void onDrawerOpened(View drawerView) {
 				super.onDrawerOpened(drawerView);
 				isActionMenuOpened = 1;
 				invalidateOptionsMenu();
 			}
 		};
 		
 		mDrawerLayout.setDrawerListener(mDrawerToggle);
 		mDrawerToggle.syncState();
 		
 		String[] values = new String[] { "Share", "Rate" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		mDrawerList.setAdapter(adapter);
//		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		
		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					Intent share = new Intent(Intent.ACTION_SEND);
					share.setType("text/plain");
					share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					share.putExtra(Intent.EXTRA_SUBJECT,
							getString(R.string.app_name));
					share.putExtra(
							Intent.EXTRA_TEXT,
							getString(R.string.app_description)
									+ "\n"
									+ "GitHub Page :  https://github.com/IkiMuhendis/LDrawer\n"
									+ "Sample App : https://play.google.com/store/apps/details?id="
									+ getPackageName());
					startActivity(Intent.createChooser(share,
					getString(R.string.app_name)));
					break;
				case 1:
					String appUrl = "https://play.google.com/store/apps/details?id="
							+ getPackageName();
					Intent rateIntent = new Intent(Intent.ACTION_VIEW,
							Uri.parse(appUrl));
					startActivity(rateIntent);
					break;
				}
			}
		});
		
//		DisplayMetrics metrics = this.getResources().getDisplayMetrics();
//		int width = metrics.widthPixels;
//		int height = metrics.heightPixels;
     
//		float heightInDp = convertPixelsToDp(Float.parseFloat(""+height), this);
//		Toast.makeText(this, ""+height, 1).show();
//		frmLayout = Views.find(this, R.id.my_frame);
//		frmLayout.getLayoutParams().height = "";
//		frmLayout.setLayoutParams(new LayoutParams(width, height));
//		frmLayout.setMinimumHeight(height);
//		ViewGroup.LayoutParams params = frmLayout.getLayoutParams();
//		params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//		frmLayout.setLayoutParams(params);
//    	srlView = Views.find(this, R.id.drawer_layout123);
//		srlView.setFitsSystemWindows(true);
//		srlView.setFillViewport(false);
//		tLayoutParams(new RelativeLayout.LayoutParams(
//                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        new ServerConnect().execute(SysytemConstants.SERVER_URL+SysytemConstants.ALL_ARTICLES);

        mListTouchInterceptor = Views.find(this, R.id.touch_interceptor_view);
        mListTouchInterceptor.setClickable(false);

        mDetailsLayout = Views.find(this, R.id.details_layout);
        mDetailsLayout.setVisibility(View.INVISIBLE);

        mUnfoldableView = Views.find(this, R.id.unfoldable_view);

        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        mUnfoldableView.setFoldShading(new GlanceFoldShading(this, glance));

        mUnfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
                mDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
                mDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
    
    
    /*
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}
		return super.onOptionsItemSelected(item);
	}
    */
    
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
				isActionMenuOpened = 0;
				
			} else {
				isActionMenuOpened = 1;
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}else{
			Toast.makeText(this, "123",
					Toast.LENGTH_SHORT).show();	
		}
		return super.onOptionsItemSelected(item);
		
	}
    

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
    
	
    @Override
    public void onBackPressed() {
    	/*
    	if (mUnfoldableView != null && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
        	mUnfoldableView.foldBack();
        	mDetailsLayout.setVisibility(View.INVISIBLE);
        	mListView.setFocusable(true);
        	mListView.requestFocus();
        }else{
        	super.onBackPressed();
        }
    	*/
    	
    
//    	Toast.makeText(getApplicationContext(), ""+isActionMenuOpened, 1).show();
    	if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
			mDrawerLayout.closeDrawer(mDrawerList);
		}else{
			/*
			if (mUnfoldableView != null && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
	        	mUnfoldableView.foldBack();
	        	mDetailsLayout.setVisibility(View.INVISIBLE);
	        	mListView.setFocusable(true);
	        	mListView.requestFocus();
	        }else{
	        	super.onBackPressed();
	        }
			
			*/
			if(isActionMenuOpened==1){
				mDrawerLayout.openDrawer(mDrawerList);
	    		isActionMenuOpened = 0;
	    	}else if (mUnfoldableView != null && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
	        	mUnfoldableView.foldBack();
	        	mDetailsLayout.setVisibility(View.INVISIBLE);
	        	mListView.setFocusable(true);
	        	mListView.requestFocus();
	        }else{
	        	super.onBackPressed();
	        }
		}
    	
    
	}
    

    public void openDetails(View coverView, Painting painting) {
        ImageView image = Views.find(mDetailsLayout, R.id.details_image);
        TextView title = Views.find(mDetailsLayout, R.id.details_title);
        TextView description = Views.find(mDetailsLayout, R.id.details_text);
        

        Picasso.with(this).load(painting.getLocation()).into(image);
        title.setText(painting.getTitle());
        description.setText(painting.getDescription());
        
        image.setFocusable(true);
        image.requestFocus();
        
        String eventUrl = SysytemConstants.ALL_EVENT_DETAILS;
		eventUrl = SysytemConstants.SERVER_URL+eventUrl.replace(SysytemConstants.VARIABLE, String.valueOf(painting.getImageId()));
        new GetEventDetails().execute(eventUrl);

        mDetailsLayout.setVisibility(View.VISIBLE);
        mUnfoldableView.unfold(coverView, mDetailsLayout);
    }
    
    
    
    class SlideMenuClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Log.d("Tag", "msg");
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    
    
    
    
    
class GetEventDetails extends AsyncTask<String, Void, String>{
		
		private ProgressDialog progressImage = null;
		
		@Override
		protected void onPreExecute() {
//			progressImage = ProgressDialog.show(UnfoldableDetailsActivity.this, "", SysytemConstants.LOADING_MESSAGE, true);
//			progressImage.setCancelable(false);
			
		}

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			ConnectToServer connect = new ConnectToServer(UnfoldableDetailsActivity.this);
			String result = connect.connectToServerBtGet(url);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			JSONArray eventImagesList = null;
			JSONArray eventArticlesList = null;
	        JSONArray eventElephantsList = null;
	        JSONArray eventMelamsList = null;
	        JSONArray eventVenuesList = null;
	        HashMap<String, String> eventImages = null;
	        HashMap<String, String> eventArticles = null;
	        HashMap<String, String> eventElephants = null;
	        HashMap<String, String> eventMelams = null;
	        HashMap<String, String> eventVenues = null;
			
			
			try {
				JSONObject reader = new JSONObject(result);
				imageUrls =  new ArrayList<String>();
				imageTitles = new ArrayList<String>();
				articleIds = new ArrayList<Integer>();
				HelperMethods helperMethods = new HelperMethods();
				eventImages = new HashMap<String, String>();
		        eventArticles = new HashMap<String, String>();
		        eventElephants = new HashMap<String, String>();
		        eventMelams = new HashMap<String, String>();
		        eventVenues = new HashMap<String, String>();
		        
		        List<Painting> eventArticlesArray = new ArrayList<Painting>();
		        List<Painting> eventElephantsArray = new ArrayList<Painting>();
		        List<Painting> eventMelamsArray = new ArrayList<Painting>();
		        List<Painting> eventVenuesArray = new ArrayList<Painting>();
		        eventImagesArray = new ArrayList<Painting>();
				
				eventImageSlider = (SliderLayout) findViewById(R.id.slider);
				eventArticleSlider = (SliderLayout) findViewById(R.id.article_slider);
				eventElephantSlider = (SliderLayout) findViewById(R.id.elephant_slider);
				eventMelamSlider  = (SliderLayout) findViewById(R.id.melam_slider);
				eventVenueSlider = (SliderLayout) findViewById(R.id.venue_slider);
				
				eventImageSlider.removeAllSliders();
				eventArticleSlider.removeAllSliders();
				eventElephantSlider.removeAllSliders();
				eventMelamSlider.removeAllSliders();
				eventVenueSlider.removeAllSliders();
				
				TextView eventImagesHeading = (TextView) findViewById(R.id.event_image_heading);
				eventImagesHeading.setText("Photos");
				TextView eventArticlesHeading = (TextView) findViewById(R.id.event_article_heading);
				eventArticlesHeading.setText("Articles");
				TextView eventElephantHeading = (TextView) findViewById(R.id.event_elephant_heading);
				eventElephantHeading.setText("Elephants");
				TextView eventMelamHeading = (TextView) findViewById(R.id.event_melam_heading);
				eventMelamHeading.setText("Melams");
				TextView eventVenueHeading = (TextView) findViewById(R.id.event_venue_heading);
				eventVenueHeading.setText("Venues");
				
				eventImagesList = reader.optJSONArray(SysytemConstants.EVENTIMAGES_RESPONSE_NAME);
				for(int i=0;i<eventImagesList.length();i++){
					JSONObject eventImage = eventImagesList.getJSONObject(i);
				    String imgLoc = eventImage.getString(SysytemConstants.EVENT_IMAGE_URL_NAME);
				    String imgDesc = eventImage.getString("imageDesc");
				    String imgTitle = eventImage.getString("eventId");
				    int imageId = eventImage.getInt("eventImageId");
//				    eventImages.put(i+"",	img);
				    Painting pt = new Painting(imageId, imgTitle, imgLoc, imgDesc, SysytemConstants.EVENT_IMAGES_TYPE_NAME);
				    eventImagesArray.add(pt);
				}
				
				helperMethods.sliderInitialize(UnfoldableDetailsActivity.this, eventImageSlider, eventImagesArray);
				
				eventArticlesList = reader.optJSONArray(SysytemConstants.EVENTARTICLES_RESPONSE_NAME);
				for(int i=0;i<eventArticlesList.length();i++){
					JSONObject eventArticle = eventArticlesList.getJSONObject(i);
					int articleId = eventArticle.getInt("eventArticleId");
					String articleDesc = eventArticle.getString("articleDescription");
				    String imgLoc = eventArticle.getString(SysytemConstants.ARTICLE_PROFILE_PIC);
				    String articleTitle = eventArticle.getString(SysytemConstants.EVENT_ARTICLE_TITLE);
				    Painting pt = new Painting(articleId, articleDesc, imgLoc, articleTitle, SysytemConstants.ARTICLES_TYPE_NAME);
				    eventArticlesArray.add(pt);
				}
				
				helperMethods.sliderInitialize(UnfoldableDetailsActivity.this, eventArticleSlider, eventArticlesArray);
				
				eventElephantsList = reader.optJSONArray(SysytemConstants.EVENTELEPHANTS_RESPONSE_NAME);
				for(int i=0;i<eventElephantsList.length();i++){
					JSONObject eventElephnat = eventElephantsList.getJSONObject(i);
					int elephantId = eventElephnat.getInt("elephantId");
					String articleDesc = eventElephnat.getString("eventId");
				    String imgLoc = eventElephnat.getString(SysytemConstants.EVENT_ELEPHANT_PROFILE_PIC);
				    String elephantTitle = eventElephnat.getString(SysytemConstants.EVENT_ELEPHANT_NAME);
				    Painting pt = new Painting(elephantId, articleDesc, imgLoc, elephantTitle, SysytemConstants.ELEPHANTS_TYPE_NAME);
				    eventElephantsArray.add(pt);
				    
				}
				
				helperMethods.sliderInitialize(UnfoldableDetailsActivity.this, eventElephantSlider, eventElephantsArray);
				
				eventMelamsList = reader.optJSONArray(SysytemConstants.EVENTMELAMS_RESPONSE_NAME);
				for(int i=0;i<eventMelamsList.length();i++){
					JSONObject eventMelam = eventMelamsList.getJSONObject(i);
				    int melamId = eventMelam.getInt("melamTeamId");
					String melamDesc = eventMelam.getString("melamTypeName");
				    String imgLoc = eventMelam.getString(SysytemConstants.EVENT_MELAM_PROFILE_PIC);
				    String melamTitle = eventMelam.getString(SysytemConstants.EVENT_MELAM_TEAMNAME);
				    Painting pt = new Painting(melamId, melamDesc, imgLoc, melamTitle, SysytemConstants.MELAMS_TYPE_NAME);
				    eventMelamsArray.add(pt);
				}
				
				helperMethods.sliderInitialize(UnfoldableDetailsActivity.this, eventMelamSlider, eventMelamsArray);
				
				
				eventVenuesList = reader.optJSONArray(SysytemConstants.EVENTVENUES_RESPONSE_NAME);
				for(int i=0;i<eventVenuesList.length();i++){
					JSONObject eventVenue = eventVenuesList.getJSONObject(i);
				    int venueId = eventVenue.getInt("venueId");
					String venueDesc = eventVenue.getString("eventId");
				    String imgLoc = eventVenue.getString(SysytemConstants.EVENT_VENUE_PROFILE_PIC);
				    String venueTitle = eventVenue.getString(SysytemConstants.EVENT_VENUE_NAME);
				    Painting pt = new Painting(venueId, venueDesc, imgLoc, venueTitle, SysytemConstants.VENUES_TYPE_NAME);
				    eventVenuesArray.add(pt);
				}
				
				helperMethods.sliderInitialize(UnfoldableDetailsActivity.this, eventVenueSlider, eventVenuesArray);
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
//			progressImage.getContext();
//			progressImage.dismiss();
			
		}
		
	}

	
	class ServerConnect extends AsyncTask<String, Void, String>{
		
		private ProgressDialog progressImage = null;
		
		
		@Override
		protected void onPreExecute() {
			progressImage = ProgressDialog.show(UnfoldableDetailsActivity.this, "", SysytemConstants.LOADING_MESSAGE, true);
			progressImage.setCancelable(false);
		}

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			ConnectToServer connect = new ConnectToServer(UnfoldableDetailsActivity.this);
			String result = connect.connectToServerBtGet(url);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			try {
				JSONObject reader = new JSONObject(result);
				//JSONObject events  = reader.getJSONObject("events");
				JSONArray jsonMainNode = reader.optJSONArray("eventArticles");
				imageUrls =  new ArrayList<String>();
				imageTitles = new ArrayList<String>();
				articleIds = new ArrayList<Integer>();
				
				for(int i=0;i<jsonMainNode.length();i++){
					
					JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
					int eventId = jsonChildNode.getInt("eventId");
					String eventName = jsonChildNode.getString("articleTitle");
					String profilPic = jsonChildNode.getString("profilePic");
					String articleDesc = jsonChildNode.getString("articleDescription");
					Painting paint = new Painting(eventId, eventName , profilPic, articleDesc);
					paintings.add(paint);
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			mListView = Views.find(UnfoldableDetailsActivity.this, R.id.list_view);
	        mListView.setAdapter(new PaintingsAdapter(UnfoldableDetailsActivity.this,paintings));
			
			progressImage.getContext();
			progressImage.dismiss();
			
			
		}
		
	}


	@Override
	public void onSliderClick(BaseSliderView slider) {
//		slider.
		Toast.makeText(this, slider.getBundle().get("sliderType") + "",
				Toast.LENGTH_SHORT).show();	
		
		switch (slider.getBundle().get("sliderType").toString()) {
		case SysytemConstants.EVENT_IMAGES_TYPE_NAME:
			Intent gallery = new Intent(UnfoldableDetailsActivity.this, GalleryView.class);
//			gallery.putExtra("data", new DataWrapper(eventImagesArray));
			SysytemConstants.eventImagesPassingArray = eventImagesArray;
			startActivity(gallery);
			break;

		default:
			break;
		}
	}
	
	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
	
	public static float convertPixelsToDp(float px, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;
	}

}
