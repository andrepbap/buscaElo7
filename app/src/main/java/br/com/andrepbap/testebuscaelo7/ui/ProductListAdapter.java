package br.com.andrepbap.testebuscaelo7.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.andrepbap.testebuscaelo7.R;
import br.com.andrepbap.testebuscaelo7.model.ProductCardModel;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductCardViewHolder> {
    private final List<ProductCardModel> productCardModelList;
    private final Context context;

    public ProductListAdapter(Context context, List<ProductCardModel> productCardModelList) {
        this.context = context;
        this.productCardModelList = productCardModelList;
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_card_view, parent, false);
        return new ProductCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        ProductCardModel productCardModel = productCardModelList.get(position);
        holder.setValues(productCardModel);
    }

    @Override
    public int getItemCount() {
        return productCardModelList.size();
    }

    class ProductCardViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productImageView;
        private final TextView productTitleTextView;
        private final TextView productNonPromotionalPriceTextView;
        private final TextView productPriceTextView;
        private final TextView productInstallmentTextView;

        public ProductCardViewHolder(@NonNull View itemView) {
            super(itemView);

            productImageView = itemView.findViewById(R.id.product_image_view);
            productTitleTextView = itemView.findViewById(R.id.product_title_text_view);
            productNonPromotionalPriceTextView = itemView.findViewById(R.id.product_non_promotional_price_text_view);
            productPriceTextView = itemView.findViewById(R.id.product_price_text_view);
            productInstallmentTextView = itemView.findViewById(R.id.product_installment_text_view);
        }

        public void setValues(ProductCardModel productCardModel) {
            Picasso.get()
                    .load(productCardModel.getPicture())
                    .fit()
                    .centerCrop()
                    .into(productImageView);
            productTitleTextView.setText(productCardModel.getTitle());

            if(productCardModel.getPrice().getNonPromotional() == null) {
                productNonPromotionalPriceTextView.setVisibility(View.GONE);
            } else {
                productNonPromotionalPriceTextView.setVisibility(View.VISIBLE);
                productNonPromotionalPriceTextView.setText(productCardModel.getPrice().getNonPromotional());
            }

            productPriceTextView.setText(productCardModel.getPrice().getCurrent());
            productInstallmentTextView.setText(productCardModel.getPrice().getInstallment());
        }
    }
}
