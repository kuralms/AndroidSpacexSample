package quick.kural.quickstart.activitys.FragmentDialouge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import quick.kural.quickstart.R;
import quick.kural.quickstart.Retrofit.Objects.spacex.RespSpaceX;


public class FragmentDialougeSpaceXDetails extends DialogFragment {

    int mNum;
    String title;
    FragmentSpacexInterface fragmentSpacexInterface;
    ImageButton ib_close;
    TextView tv_wiki,tv_youtube ,
            tv_misson,tv_rocket,
            tv_customer,tv_launch_date,
            tv_flight_id,tv_description,
            tv_engine_type,tv_engine_name;
    RespSpaceX spacexResp;


    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static FragmentDialougeSpaceXDetails newInstance(String title, int verifyType , RespSpaceX spacexResp) {
        FragmentDialougeSpaceXDetails f = new FragmentDialougeSpaceXDetails();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("verifyType", verifyType);
        args.putSerializable("dataObj", spacexResp);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentDialougeSpaceXDetails.FragmentSpacexInterface) {
            fragmentSpacexInterface = (FragmentDialougeSpaceXDetails.FragmentSpacexInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AcceptGdprInterfaceInteractionListener");
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("verifyType");
        title = getArguments().getString("title");
        spacexResp = (RespSpaceX) getArguments().getSerializable("dataObj");


     /*   // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(style, theme);*/

        setStyle(DialogFragment.STYLE_NO_FRAME,  DialogFragment.STYLE_NO_TITLE);




    }

    @Override
    public View onCreateView(LayoutInflater inflater,
           ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_show_spacex_details, container, false);

                tv_wiki = v.findViewById(R.id.tv_fssd_wiki);
                tv_youtube = v.findViewById(R.id.tv_fssd_youtube);
                ib_close = v.findViewById(R.id.ib_fssd_close);
                tv_misson  = v.findViewById(R.id.tv_fssd_mission_name);
                tv_rocket  = v.findViewById(R.id.tv_fssd_rocket_name);
                tv_customer = v.findViewById(R.id.tv_fssd_client);
                tv_launch_date  = v.findViewById(R.id.tv_fssd_launch_date);
                tv_flight_id  = v.findViewById(R.id.tv_fssd_flight_id);
                tv_description = v.findViewById(R.id.tv_fssd_rocket_description);
                tv_engine_type = v.findViewById(R.id.tv_fssd_engine_type);
                tv_engine_name  = v.findViewById(R.id.tv_fssd_engine_ver);



        tv_wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(spacexResp.getLinks().getWikipedia());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                getActivity().startActivity(intent);
            }
        });
        tv_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(spacexResp.getLinks().getVideoLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                getActivity().startActivity(intent);
            }
        });


        tv_misson.setText( spacexResp.getMissionName());
        tv_rocket.setText( spacexResp.getRocket().getRocketName());

        String customer  = spacexResp.getRocket().getSecondStage().getPayloads().get(0).getCustomers().get(0);


        tv_customer.setText(customer);
        tv_launch_date.setText( spacexResp.getLaunchDateLocal().toString());
        tv_flight_id.setText( spacexResp.getFlightNumber().toString());
        tv_description.setText( spacexResp.getDetails().toString());
        tv_engine_type.setText( spacexResp.getLaunchYear().toString());
        tv_engine_name.setText( spacexResp.getMissionName().toString());



     ib_close.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             dismiss();

         }
     });


        return v;
    }



    public interface FragmentSpacexInterface{

    void btn_close();

    }

}
