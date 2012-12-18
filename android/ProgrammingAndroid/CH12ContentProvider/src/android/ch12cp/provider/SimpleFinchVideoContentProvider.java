package android.ch12cp.provider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

public class SimpleFinchVideoContentProvider extends ContentProvider {
	
	public static final String SIMPLE_VIDEO = "simple_video";
	public static final String DATABASE_NAME = SIMPLE_VIDEO + ".db";
	public static final int DATABASE_VERSION = 3;
	public static final String VIDEO_TABLE_NAME = "video";
	
	private DatabaseHelper mOpenHelper;
	
	private static UriMatcher sUriMatcher;
	
	private static final int VIDEOS = 1;
	private static final int VIDEO_ID = 2;
	
	private static HashMap<String, String> sVideoProjectionMap;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(FinchVideo.SIMPLE_AUTHORITY,
				FinchVideo.SimpleVideos.VIDEO_NAME,
				VIDEOS);
		sUriMatcher.addURI(FinchVideo.SIMPLE_AUTHORITY,
				FinchVideo.SimpleVideos.VIDEO_NAME + "/#",
				VIDEO_ID);
		
		sVideoProjectionMap = new HashMap<String, String>();
		sVideoProjectionMap.put(FinchVideo.SimpleVideos._ID,
				FinchVideo.SimpleVideos._ID);
		sVideoProjectionMap.put(FinchVideo.SimpleVideos.TITLE_NAME,
				FinchVideo.SimpleVideos.TITLE_NAME);
		sVideoProjectionMap.put(FinchVideo.SimpleVideos.DESCRIPTION_NAME,
				FinchVideo.SimpleVideos.DESCRIPTION_NAME);
		sVideoProjectionMap.put(FinchVideo.SimpleVideos.VIDEO_NAME,
				FinchVideo.SimpleVideos.VIDEO_NAME);
	}

	public SimpleFinchVideoContentProvider() {
	}

	@Override
	public boolean onCreate() {
		mOpenHelper = new DatabaseHelper(getContext(), DATABASE_NAME, null);
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case VIDEOS:
			return FinchVideo.SimpleVideos.CONTENT_TYPE;
		case VIDEO_ID:
			return FinchVideo.SimpleVideos.CONTENT_VIDEO_TYPE;
		default:
			throw new IllegalArgumentException("Unknown video type: " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		
		String orderBy = FinchVideo.SimpleVideos.DEFAULT_SORT_ORDER;
		if (!TextUtils.isEmpty(sortOrder)) {
			orderBy = sortOrder;
		}
		
		int match = sUriMatcher.match(uri);
		Cursor c;
		
		switch (match) {
		case VIDEOS:
			c = db.query(VIDEO_TABLE_NAME, projection, selection,
					selectionArgs, null, null, orderBy);
			c.setNotificationUri(getContext().getContentResolver(),
					FinchVideo.SimpleVideos.CONTENT_URI);
			break;
		case VIDEO_ID:
			long videoId = ContentUris.parseId(uri);
			c = db.query(VIDEO_TABLE_NAME, projection,
					FinchVideo.SimpleVideos._ID + " = " + videoId +
					(!TextUtils.isEmpty(selection) ?
							" AND (" + selection + ")" : ""),
					selectionArgs, null, null, orderBy);
			c.setNotificationUri(getContext().getContentResolver(),
					FinchVideo.SimpleVideos.CONTENT_URI);
			break;
		default:
			throw new IllegalArgumentException("unsupported uri: " + uri);
		}
		
		return c;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		
		if (sUriMatcher.match(uri) != VIDEOS) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}
		
		verifyValues(values);
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		
		long rowId = db.insert(VIDEO_TABLE_NAME, FinchVideo.SimpleVideos.VIDEO_NAME, values);
		if (rowId > 0) {
			Uri videoUri = ContentUris.withAppendedId(
					FinchVideo.SimpleVideos.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(videoUri, null);
			return videoUri;
		}
		
		throw new SQLException("Failed to insert row into " + uri);
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int affected;
		
		switch (sUriMatcher.match(uri)) {
		case VIDEOS:
			affected = db.update(VIDEO_TABLE_NAME, values,
					selection, selectionArgs);
			break;
		case VIDEO_ID:
			String videoId = uri.getPathSegments().get(1);
			affected = db.update(VIDEO_TABLE_NAME, values,
					FinchVideo.SimpleVideos._ID + " = " + videoId +
					(!TextUtils.isEmpty(selection) ?
							" AND (" + selection + ")" : ""),
					selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		return affected;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int affected;
		
		switch (sUriMatcher.match(uri)) {
		case VIDEOS:
			affected = db.delete(VIDEO_TABLE_NAME,
					!TextUtils.isEmpty(selection) ?
							" AND (" + selection + ")" : "",
					selectionArgs);
			break;
		case VIDEO_ID:
			long videoId = ContentUris.parseId(uri);
			affected = db.delete(VIDEO_TABLE_NAME,
					FinchVideo.SimpleVideos._ID + " = " + videoId + 
					(!TextUtils.isEmpty(selection) ?
							" AND (" + selection + ")" : ""),
					selectionArgs);
			
			getContext().getContentResolver().notifyChange(uri, null);
			break;
		default:
			throw new IllegalArgumentException("Unknown video element: " + uri);
		}
		
		return affected;
	}
	
	private void verifyValues(ContentValues vs) {
		
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name,
				CursorFactory factory) {
			super(context, name, factory, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			createTable(db);
		}


		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " +
					VIDEO_TABLE_NAME + ";");
			createTable(db);
		}

		private void createTable(SQLiteDatabase db) {
			String sql = "CREATE TABLE " + VIDEO_TABLE_NAME + " (" +
					BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					FinchVideo.SimpleVideos.TITLE_NAME + " TEXT, " +
					FinchVideo.SimpleVideos.DESCRIPTION_NAME + " TEXT, " +
					FinchVideo.SimpleVideos.URI_NAME + " TEXT);";
			db.execSQL(sql);
		}
	}
}
