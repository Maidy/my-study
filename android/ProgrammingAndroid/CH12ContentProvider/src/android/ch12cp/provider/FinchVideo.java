package android.ch12cp.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class FinchVideo {

	public static final int ID_COLUMN = 0;
	public static final int TITLE_COLUMN = 1;
	public static final int DESCRIPTION_COLUMN = 2;
	public static final int URI_COLUMN = 3;

	public static final String SIMPLE_AUTHORITY =
			"android.ch12cp.finchvideo.SimpleFinchVideo";
	
	public static final class SimpleVideos implements BaseColumns {
		
		private SimpleVideos() { }
		
		public static final String DEFAULT_SORT_ORDER = "";
		
		// uris
		public static final Uri VIDEOS_URI =
				Uri.parse("content://" + SIMPLE_AUTHORITY + "/" + SimpleVideos.VIDEO_NAME);
		public static final Uri CONTENT_URI = VIDEOS_URI;

		// mime types
		public static final String CONTENT_TYPE = 
				"vnd.android.cursor.dir/vnd.finch.video";
		public static final String CONTENT_VIDEO_TYPE = 
				"vnd.android.cursor.item/vnd.finch.video";
		
		public static final String VIDEO_NAME = "video";
		
		// column names
		public static final String TITLE_NAME = "title";
		public static final String DESCRIPTION_NAME = "description";
		public static final String URI_NAME = "uri";
	}
}
