package com.airhockey.android.util;

import static android.opengl.GLES20.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class TextureHelper {

	private static final String TAG = TextureHelper.class.getSimpleName();

	public static int loadTexture(Context context, int resourceId) {
		
		// texture 객체 생성
		final int[] textureObjectIds = new int[1];
		glGenTextures(1, textureObjectIds, 0);
		
		// 생성 실패 확인
		if (textureObjectIds[0] ==0) {
			if (LoggerConfig.ON)
				Log.w(TAG, "Counld not generate a new OpenGL texture object.");
			return 0;
		}
		
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;
		
		// texture에 입힐 bitmap 생성
		final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
		if (bitmap == null) {
			if (LoggerConfig.ON)
				Log.w(TAG, "Resource ID " + resourceId + " could not be decoded");
			glDeleteTextures(1, textureObjectIds, 0);
			return 0;
		}
		
		// 이후 texture call은 textureObjectIds[0]를 대상으로 한다.
		glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);
		
		// texture filtering 설정
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		// 이미지 로딩
		GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
		
		// 이미지 로딩했으니 bitmap 필요없다.
		bitmap.recycle();
		
		// mipmap을 사용하다고 했으니 생성해 주기
		glGenerateMipmap(GL_TEXTURE_2D);
		
		// 여기까지 해서 생성 및 설정 완료
		
		// texture unbind - 실수하지 말라고
		glBindTexture(GL_TEXTURE_2D, 0);
		
		return textureObjectIds[0];
	}
}
