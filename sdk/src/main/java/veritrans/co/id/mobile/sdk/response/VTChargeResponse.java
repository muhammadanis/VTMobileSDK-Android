package veritrans.co.id.mobile.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muhammadanis on 2/2/15.
 */
public class VTChargeResponse
{
    private String approval_code;

    private String status_code;

    private String transaction_time;

    private String gross_amount;

    private String transaction_status;

    private String masked_card;

    private String fraud_status;

    private String status_message;

    private String bank;

    private String order_id;

    private String payment_type;

    private String transaction_id;

    public String getApproval_code ()
    {
        return approval_code;
    }

    public void setApproval_code (String approval_code)
    {
        this.approval_code = approval_code;
    }

    public String getStatus_code ()
    {
        return status_code;
    }

    public void setStatus_code (String status_code)
    {
        this.status_code = status_code;
    }

    public String getTransaction_time ()
    {
        return transaction_time;
    }

    public void setTransaction_time (String transaction_time)
    {
        this.transaction_time = transaction_time;
    }

    public String getGross_amount ()
    {
        return gross_amount;
    }

    public void setGross_amount (String gross_amount)
    {
        this.gross_amount = gross_amount;
    }

    public String getTransaction_status ()
    {
        return transaction_status;
    }

    public void setTransaction_status (String transaction_status)
    {
        this.transaction_status = transaction_status;
    }

    public String getMasked_card ()
    {
        return masked_card;
    }

    public void setMasked_card (String masked_card)
    {
        this.masked_card = masked_card;
    }

    public String getFraud_status ()
    {
        return fraud_status;
    }

    public void setFraud_status (String fraud_status)
    {
        this.fraud_status = fraud_status;
    }

    public String getStatus_message ()
    {
        return status_message;
    }

    public void setStatus_message (String status_message)
    {
        this.status_message = status_message;
    }

    public String getBank ()
    {
        return bank;
    }

    public void setBank (String bank)
    {
        this.bank = bank;
    }

    public String getOrder_id ()
    {
        return order_id;
    }

    public void setOrder_id (String order_id)
    {
        this.order_id = order_id;
    }

    public String getPayment_type ()
    {
        return payment_type;
    }

    public void setPayment_type (String payment_type)
    {
        this.payment_type = payment_type;
    }

    public String getTransaction_id ()
    {
        return transaction_id;
    }

    public void setTransaction_id (String transaction_id)
    {
        this.transaction_id = transaction_id;
    }
}
