package menu;

import static menu.GUI.getBackArrow;
import static menu.GUI.getNavButt;
import static menu.GUI.left;
import static menu.GUI.right;

import game.GAME;
import game.VERSION;
import init.C;
import init.sprite.UI.UI;
import init.text.D;
import menu.GUI.Button;
import snake2d.CORE;
import snake2d.CORE_STATE;
import snake2d.CORE_STATE.Constructor;
import snake2d.SPRITE_RENDERER;
import snake2d.util.datatypes.COORDINATE;
import snake2d.util.gui.GuiSection;
import snake2d.util.gui.clickable.CLICKABLE;
import snake2d.util.gui.renderable.RENDEROBJ;
import snake2d.util.misc.ACTION;
import util.gui.misc.GText;
import view.main.VIEW;
import view.menu.MenuScreenLoad;

class ScMain implements SC{

	private final GuiSection first;
	private final GuiSection play;
	private final GuiSection scenario;
	private GuiSection current;
	private final RENDEROBJ.Sprite logo;
	private final Menu menu;
	private final GText version = new GText(UI.FONT().H2, VERSION.VERSION_STRING);
	ScMain(Menu menu) {

		D.t(this);
		this.menu = menu;
		first = getFirst(menu);
		play = getPlay(menu);
		play.body().moveY1(first.body().y1());
		scenario = getScenario(menu);
		scenario.body().moveY1(first.body().y1());

		logo = new RENDEROBJ.Sprite(menu.res.s().logo);
		logo.body().moveX2(left.x2());
		logo.body().centerY(left);
		logo.setColor(GUI.COLORS.menu);



		current = first;

	}

	private static CharSequence ¤¤continue = "continue";
	private static CharSequence ¤¤quit = "quit";
	private static CharSequence ¤¤play = "play";
	private static CharSequence ¤¤scenario = "scenario";
	private static CharSequence ¤¤editor = "editor";

	static {
		D.ts(ScMain.class);
	}

	private GuiSection getFirst(Menu menu){

		GuiSection current = new GuiSection();
		CLICKABLE text;

		text = getNavButt(¤¤play);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				switchNavigator(play);
			}
		});
		current.addDown(0, text);

		text = new Button(UI.FONT().H1.getText(¤¤continue)) {
			@Override
			protected void render(SPRITE_RENDERER r, float ds, boolean isActive, boolean isSelected,
								  boolean isHovered) {
				activeSet(menu.load.hasSaves());
				super.render(r, ds, isActive, isSelected, isHovered);
			}

			@Override
			protected void clickA() {
				if (menu.load.hasSaves())
					menu.load.loadSave();
			}
		};
		current.addDown(8, text);

		text = getNavButt(ScOptions.¤¤name);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				menu.switchScreen(menu.options);
			}
		});
		current.addDown(8, text);

		text = getNavButt(ScCredits.¤¤name);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				menu.switchScreen(menu.credits);
			}
		});
		current.addDown(8, text);

		// MODDED: adds another menu entry opening the credits
		text = getNavButt("Example Entry (Credits)");
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				menu.switchScreen(menu.credits);
			}
		});
		current.addDown(8, text);

		text = getNavButt(¤¤quit);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				CORE.annihilate();
			}
		});
		current.addDown(8, text);

		current.body().moveX1(right.x1());
		current.body().centerY(right.y1(), right.y2());

		return current;
	}


	private GuiSection getPlay(Menu menu){

		GuiSection current = new GuiSection();

		CLICKABLE text;

		text = getNavButt(MenuScreenLoad.¤¤name);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				menu.switchScreen(menu.load);
			}
		});
		current.addDown(0, text);

		text = getNavButt(ScCampaign.¤¤name);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				menu.switchScreen(menu.campaigns);

			}
		});
		current.addDown(8, text);


		text = getNavButt(ScRandom.¤¤name);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				menu.switchScreen(menu.sandbox);
			}
		});
		current.addDown(8, text);



		text = getNavButt(¤¤scenario);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				switchNavigator(scenario);
			}
		});
		current.addDown(8, text);

		text = getBackArrow();
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				switchNavigator(first);
			}
		});
		current.addDown(10, text);

		current.body().moveX1(right.x1());
		current.body().centerY(right);

		return current;
	}

	private GuiSection getScenario(Menu menu){

		GuiSection current = new GuiSection();

		CLICKABLE text;

		for (ScLoad l : menu.loads) {
			text = getNavButt(l.name);
			text.clickActionSet(new ACTION() {
				@Override
				public void exe() {
					menu.switchScreen(l);
				}
			});
			if (!l.hasSaves())
				text.activeSet(false);
			current.addDown(8, text);
		}

		text = getNavButt(¤¤editor);
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				menu.start(new Constructor() {

					@Override
					public CORE_STATE getState() {
						CORE_STATE s = GAME.create();

						VIEW.world().editor.activate();

						return s;
					}
				});
			}
		});
		current.addDown(8, text);


		text = getBackArrow();
		text.clickActionSet(new ACTION() {
			@Override
			public void exe() {
				switchNavigator(play);
			}
		});
		current.addDown(10, text);

		current.body().moveX1(right.x1());
		current.body().centerY(right);

		return current;
	}

	private void switchNavigator(GuiSection section){
		current = section;
		current.hover(menu.getMCoo());
	}

	@Override
	public void render(SPRITE_RENDERER r, float ds) {
		logo.render(r, ds);
		current.render(r, ds);
		version.render(r, C.DIM().x2()-32-version.width(), 32);
	}

	@Override
	public boolean hover(COORDINATE mCoo) {
		return current.hover(mCoo);
	}

	@Override
	public boolean click() {
		return current.click();
	}

	@Override
	public boolean back(Menu menu) {
		if (current != first) {
			if (current == scenario)
				switchNavigator(play);
			else
				switchNavigator(first);
			return true;
		}
		return false;
	}


}
