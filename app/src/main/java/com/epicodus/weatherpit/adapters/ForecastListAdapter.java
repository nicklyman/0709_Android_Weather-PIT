package com.epicodus.weatherpit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.weatherpit.R;
import com.epicodus.weatherpit.models.Forecast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {
    private ArrayList<Forecast> mForecasts = new ArrayList<>();
    private Context mContext;

    public ForecastListAdapter(Context context, ArrayList<Forecast> forecasts) {
        mContext = context;
        mForecasts = forecasts;
    }

    @Override
    public ForecastListAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_forecast_list_item, parent, false);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastListAdapter.ForecastViewHolder holder, int position) {
        holder.bindForecast(mForecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dailyTimeTextView) TextView mDailyTimeTextView;
        @Bind(R.id.dailySummaryTextView) TextView mDailySummaryTextView;

        private Context mContext;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindForecast(Forecast forecast) {
            mDailySummaryTextView.setText(forecast.getDailySummary());
        }
    }
}
