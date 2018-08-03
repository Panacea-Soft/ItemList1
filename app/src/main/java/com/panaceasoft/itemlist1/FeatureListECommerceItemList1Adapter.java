package com.panaceasoft.itemlist1;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Panacea-Soft on 2/8/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class FeatureListECommerceItemList1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ShopItem> shopItemList;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, ShopItem obj, int position);
        void onAddToCartClick(View view, ShopItem obj, int position);
        void onMenuClick(View view, ShopItem obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public FeatureListECommerceItemList1Adapter(List<ShopItem> shopItemList) {
        this.shopItemList = shopItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_list_ecommerce_item_list_1_item, parent, false);

        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof PlaceViewHolder) {

            ShopItem shopItem = shopItemList.get(position);

            PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
            holder.itemNameTextView.setText(shopItem.name);

            Context context = holder.holderCardView.getContext();

            int id = Utils.getDrawableInt(context, shopItem.imageName);
            Utils.setImageToImageView(context, holder.itemImageView, id);

            holder.categoryTextView.setText(shopItem.categoryName);
            String priceStr = shopItem.currency + " " + shopItem.price;
            holder.priceTextView.setText(priceStr);

            String originalPriceStr = shopItem.currency + " " + shopItem.originalPrice;
            holder.originalPriceTextView.setText(originalPriceStr);

            holder.ratingBar.setRating(Float.parseFloat(shopItem.totalRating));
            String ratingCountStr = "(" + shopItem.totalRating + ")";
            holder.ratingCountTextView.setText(ratingCountStr);

            if (shopItem.discount != null && Integer.parseInt(shopItem.discount) > 0) {
                holder.promoCardView.setVisibility(View.VISIBLE);
                String discount = shopItem.discount + " %";
                holder.promoAmtTextView.setText(discount);
                holder.originalPriceTextView.setPaintFlags(holder.originalPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.promoCardView.setVisibility(View.GONE);
                holder.originalPriceTextView.setVisibility(View.GONE);
            }

            if ( itemClickListener != null ) {
                holder.holderCardView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, shopItemList.get(position), position));
                holder.addToBasketImageView.setOnClickListener((View v) -> itemClickListener.onAddToCartClick(v, shopItemList.get(position), position));
                holder.menuImageView.setOnClickListener((View v) -> itemClickListener.onMenuClick(v, shopItemList.get(position), position));
            }

        }
    }

    @Override
    public int getItemCount() {
        return shopItemList.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        RatingBar ratingBar;
        TextView itemNameTextView;
        TextView categoryTextView;
        TextView priceTextView;
        TextView originalPriceTextView;
        TextView ratingCountTextView;
        TextView promoAmtTextView;
        CardView promoCardView;
        CardView holderCardView;
        ImageView addToBasketImageView;
        ImageView menuImageView;

        PlaceViewHolder(View view) {
            super(view);

            itemImageView = view.findViewById(R.id.itemImageView);
            ratingBar = view.findViewById(R.id.ratingBar);
            itemNameTextView = view.findViewById(R.id.itemNameTextView);
            categoryTextView = view.findViewById(R.id.categoryTextView);
            priceTextView = view.findViewById(R.id.priceTextView);
            originalPriceTextView = view.findViewById(R.id.originalPriceTextView);
            ratingCountTextView = view.findViewById(R.id.ratingCountTextView);
            promoAmtTextView = view.findViewById(R.id.promoAmtTextView);
            promoCardView = view.findViewById(R.id.promoCardView);
            holderCardView = view.findViewById(R.id.holderCardView);
            addToBasketImageView = view.findViewById(R.id.addToCartImageView);
            menuImageView = view.findViewById(R.id.menuImageView);

        }
    }
}
