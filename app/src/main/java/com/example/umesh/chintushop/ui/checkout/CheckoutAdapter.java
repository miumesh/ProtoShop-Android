package com.example.umesh.chintushop.ui.checkout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umesh.chintushop.R;
import com.example.umesh.chintushop.core.listeners.CartActionsListener;
import com.example.umesh.chintushop.model.LineItem;
import com.example.umesh.chintushop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Umesh on 27-09-2017.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder>{

    private final List<LineItem> mLineItems;
    private final Activity mContext;
    private final CartActionsListener mListener;

    public CheckoutAdapter(List<LineItem> lineItems, Context context, CartActionsListener listener) {
        mLineItems = lineItems;
        mContext = (Activity) context;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shopping_cart_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LineItem item = mLineItems.get(position);
        Picasso.with(mContext)
                .load(item.getImagePath())
                .fit()
                .placeholder(R.drawable.default_image)
                .into(holder.productImage);
        holder.productName.setText(item.getProductName());
        holder.price.setText(Formatter.formatCurrency(item.getSalePrice()));
        holder.qtyEditText.setText(String.valueOf(item.getQuantity()));


    }

    @Override
    public int getItemCount() {
        if (mLineItems != null) {
            return mLineItems.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            @Bind(R.id.product_image) ImageView productImage;
            @Bind(R.id.text_view_product_name) TextView productName;
            @Bind(R.id.text_view_price) TextView price;
            @Bind(R.id.edit_text_qty) EditText qtyEditText;
            @Bind(R.id.button_delete) Button deleteButton;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            deleteButton.setOnClickListener(this);
            qtyEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LineItem item = mLineItems.get(getLayoutPosition());
                    updateQtyDialog(item);

                }


            });
        }


        @Override
        public void onClick(View view) {

            LineItem item = mLineItems.get(getLayoutPosition());
            mListener.onItemDeleted(item);

        }

        private void updateQtyDialog(final LineItem item) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            LayoutInflater inflater = mContext.getLayoutInflater();

            View rootView = inflater.inflate(R.layout.dialog_enter_item_qty, null);
            dialog.setView(rootView);

            View titleView = inflater.inflate(R.layout.dialog_title,null);
            TextView titleText = (TextView)titleView.findViewById(R.id.text_view_dialog_title);
            titleText.setText(item.getProductName());
            dialog.setCustomTitle(titleView);

            final EditText qtyEditText = (EditText)rootView.findViewById(R.id.edit_text_item_qty);
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (qtyEditText.getText().toString().isEmpty()){
                        int qtyEntered = Integer.parseInt(qtyEditText.getText().toString());
                        mListener.onItemQtyChange(item, qtyEntered);
                    }else {
                        Toast.makeText(mContext, "Invalid Qty", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                }
            });
            dialog.show();

        }
    }
}
