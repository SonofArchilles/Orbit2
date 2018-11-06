package com.jacobjacob.orbit;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Buttons extends MainActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Image = findViewById(R.id.image);
        xscreen = size.x;
        yscreen = size.y;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        ToggleButton menu = findViewById(R.id.menu);
        menu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Menu = true;
                } else {
                    Menu = false;
                }
                newMenu();
            }
        });
        ToggleButton DrawTrails = findViewById(R.id.drawtrails);
        DrawTrails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drawTrails = true;
                } else {
                    drawTrails = false;
                }
                newMenu();
            }
        });

        ToggleButton DrawLines = findViewById(R.id.drawlines);
        DrawLines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drawLines = true;
                } else {
                    drawLines = false;
                }
                newMenu();
            }
        });

        ToggleButton Collision = findViewById(R.id.collisions);
        Collision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    collision = true;
                } else {
                    collision = false;
                }
                newMenu();
            }
        });

        ToggleButton symetry = findViewById(R.id.symmetry);
        symetry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Symmetry = true;
                } else {
                    Symmetry = false;
                }
                newMenu();
                Reset1();
            }
        });
        ToggleButton acc = findViewById(R.id.accel);
        acc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawacc = true;
                } else {
                    Drawacc = false;
                }
                draw();
                newMenu();
            }
        });

        ToggleButton screenlimit = findViewById(R.id.screenlimit);
        screenlimit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Canleavescreen = true;
                } else {
                    Canleavescreen = false;
                }
                newMenu();
            }
        });
        ToggleButton Rotate1 = findViewById(R.id.Rot);
        Rotate1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Rotate = true;
                } else {
                    Rotate = false;
                }
                newMenu();
            }
        });
        ToggleButton mov = findViewById(R.id.move);
        mov.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Move = true;
                } else {
                    Move = false;
                }
                newMenu();
            }
        });
        ToggleButton zom = findViewById(R.id.zoom);
        zom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Zoom = true;
                } else {
                    Zoom = false;
                }
                newMenu();
            }
        });

        ToggleButton reset = findViewById(R.id.reset);
        reset.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                xmove1 = 0;
                ymove1 = 0;
                xmove = 0;
                ymove = 0;
                Reset1();
                newMenu();
            }
        });
        final Button symcounter = (Button) findViewById(R.id.symcounter);
        symcounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.text);
                if (Symmetry) {

                    symmetry += 1;
                }
                if (ranP & !Symmetry) {
                    randomplanets += 1;
                    if (randomplanets > 20) {
                        randomplanets = 20;
                    }
                }
                Reset1();
            }
        });
        final Button symcountermin = (Button) findViewById(R.id.symcountermin);
        symcountermin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.text);
                if (Symmetry) {

                    symmetry -= 1;
                    if (symmetry < 0) {
                        symmetry = 0;
                    }
                }
                if (ranP & !Symmetry) {
                    randomplanets -= 1;
                    if (randomplanets < 0) {
                        randomplanets = 0;
                    }
                }
                Reset1();
            }
        });

        ToggleButton Drawvel = findViewById(R.id.drawVel);
        Drawvel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drawvel = true;
                } else {
                    drawvel = false;
                }
                newMenu();
            }
        });

        ToggleButton ranp = findViewById(R.id.ranp);
        ranp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ranP = true;
                } else {
                    ranP = false;
                }
                newMenu();
            }
        });
        ToggleButton Selectp = findViewById(R.id.selcp);
        Selectp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selecPlanet = true;
                } else {
                    selecPlanet = false;
                }
                newMenu();
            }
        });
        SeekBar seekBar = findViewById(R.id.seekangle);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rotangle = progress - seekstart;

                if (Symmetry & symmetry > 0 || Rotate) {
                    //angle = 360 / symmetry + rotangle;
                    for (int i = 0; i < Planets.size(); i++) {
                        if (i != 0) {

                            symmetrydist = (float) Math.sqrt(Math.pow(Planets.get(0).x - Planets.get(i).x, 2) + Math.pow(Planets.get(0).y - Planets.get(i).y, 2));

                            float angle2 = (float) Math.toDegrees(Planets.get(i).angle1) + rotangle;

                            distSunx = (float) (symmetrydist * Math.cos(Math.toRadians(angle2))); //funktioniert
                            distSuny = (float) (symmetrydist * Math.sin(Math.toRadians(angle2))); //funktioniert

                            angle = (float) Math.atan2(Planets.get(i).vy1, Planets.get(i).vx1) + (float) Math.toRadians(rotangle); //+ (float) Math.toRadians(angle2);


                            float dist2 = (float) Math.sqrt(Math.pow(Planets.get(i).vx1, 2) + Math.pow(Planets.get(i).vy1, 2)); //funktioniert
                            Planets.get(i).vx = (float) Math.cos(angle) * dist2; //funktioniert
                            Planets.get(i).vy = (float) Math.sin(angle) * dist2; //funktioniert

                            Planets.get(i).x = Planets.get(0).x + distSunx;
                            Planets.get(i).y = Planets.get(0).y + distSuny;
                        }
                    }
                    draw();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }
        });
        SeekBar seekzom = findViewById(R.id.seekzoom);
        seekzom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                zoom = (float) Math.sqrt(progress);
                draw();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }
        });


    }
*/
}


