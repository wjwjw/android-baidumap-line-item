package com.example.baidumapdrivermuti;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;

import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.Projection;

class LineOverlay extends Overlay {

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);

		Projection projection = mapView.getProjection();
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		// ����ת����
		projection.toPixels(MyMapActivity.GeoPointsVec.get(0), p1);
		projection.toPixels(MyMapActivity.GeoPointsVec.get(1), p2);
		projection.toPixels(MyMapActivity.GeoPointsVec.get(2), p3);

		// ��һ������ ��Բ
		Paint fillPaint = new Paint();
		fillPaint.setColor(Color.BLUE);
		fillPaint.setAntiAlias(true);
		fillPaint.setStyle(Style.FILL);

		// ��ͼ�����ϲ�
		canvas.drawCircle(p1.x, p1.y, 5.0f, fillPaint);
		canvas.drawCircle(p2.x, p2.y, 5.0f, fillPaint);
		canvas.drawCircle(p3.x, p3.y, 5.0f, fillPaint);

		// �ڶ������� ����
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setDither(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(4);

		// ����
		Path path = new Path();
		path.moveTo(p1.x, p1.y);
		path.lineTo(p2.x, p2.y);
		path.lineTo(p3.x, p3.y);
		// ����·��
		canvas.drawPath(path, paint);
	}
}

