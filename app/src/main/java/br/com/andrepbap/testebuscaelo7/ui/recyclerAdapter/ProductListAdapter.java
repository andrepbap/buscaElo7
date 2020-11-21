package br.com.andrepbap.testebuscaelo7.ui.recyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.andrepbap.testebuscaelo7.R;
import br.com.andrepbap.testebuscaelo7.model.ProductCardModel;
import br.com.andrepbap.testebuscaelo7.ui.ProductActivity;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductCardViewHolder> {
    private List<ProductCardModel> productCardModelList;
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

    public void refreshWith(List<ProductCardModel> productCardModelList) {
        this.productCardModelList = productCardModelList;
        notifyDataSetChanged();
    }

    class ProductCardViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productImageView;
        private final TextView productTitleTextView;
        private final TextView productNonPromotionalPriceTextView;
        private final TextView productPriceTextView;
        private final TextView productInstallmentTextView;
        private final CardView cardView;

        public ProductCardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
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

            setupTextView(productTitleTextView, productCardModel.getTitle());
            setupTextView(productNonPromotionalPriceTextView, productCardModel.getPrice().getNonPromotional());
            setupTextView(productPriceTextView, productCardModel.getPrice().getCurrent());
            setupTextView(productInstallmentTextView, productCardModel.getPrice().getInstallment());

            cardView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.URL, productCardModel.getLink());
                context.startActivity(intent);
            });
        }

        private void setupTextView(TextView view, String value) {
            if(value == null) {
                view.setVisibility(View.GONE);
                return;
            }

            view.setVisibility(View.VISIBLE);
            view.setText(value);
        }
    }
}
