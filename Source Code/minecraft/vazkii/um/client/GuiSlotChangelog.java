package vazkii.um.client;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Tessellator;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * Apologies for having rewritten the GuiSlot class, but if I had extended it it would have placed a scroll bar in the middle of the GUI, so I had to do it like this.
 * 
 * @author Vazkii
 */
public class GuiSlotChangelog {

	private final Minecraft mc;
	private final int width;
	private final int height;
	protected final int top;
	protected final int bottom;
	private final int right;
	private final int left;
	protected final int slotHeight;
	private int scrollUpButtonID;
	private int scrollDownButtonID;
	protected int mouseX;
	protected int mouseY;
	private float initialClickY = -2.0F;
	private float scrollMultiplier;
	private float amountScrolled;
	private int selectedElement = -1;
	private long lastClicked = 0L;
	private boolean field_25123_p = true;
	private int field_27261_r;

	GuiChangelog parentGui;

	public GuiSlotChangelog(GuiChangelog parent) {
		parentGui = parent;
		mc = ModLoader.getMinecraftInstance();
		width = parent.width;
		height = parent.height;
		top = 32;
		bottom = height - 16;
		slotHeight = 12;
		left = 0;
		right = width;
	}

	public void func_27258_a(boolean par1) {
		field_25123_p = par1;
	}

	protected void func_27259_a(boolean par1, int par2) {
		field_27261_r = par2;

		if (!par1) field_27261_r = 0;
	}

	public int func_27256_c(int par1, int par2) {
		int var3 = width / 2 - 110;
		int var4 = width / 2 + 110;
		int var5 = par2 - top - field_27261_r + (int) amountScrolled - 4;
		int var6 = var5 / slotHeight;
		return par1 >= var3 && par1 <= var4 && var6 >= 0 && var5 >= 0 && var6 < getSize() ? var6 : -1;
	}

	private void bindAmountScrolled() {
		int var1 = getContentHeight() - (bottom - top - 4);

		if (var1 < 0) var1 /= 2;

		if (amountScrolled < 0.0F) amountScrolled = 0.0F;

		if (amountScrolled > var1) amountScrolled = var1;
	}

	public void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) if (par1GuiButton.id == scrollUpButtonID) {
			amountScrolled -= slotHeight * 2 / 3;
			initialClickY = -2.0F;
			bindAmountScrolled();
		}
		else if (par1GuiButton.id == scrollDownButtonID) {
			amountScrolled += slotHeight * 2 / 3;
			initialClickY = -2.0F;
			bindAmountScrolled();
		}
	}

	public void drawScreen(int par1, int par2, float par3) {
		mouseX = par1;
		mouseY = par2;
		drawBackground();
		int var4 = getSize();
		int var5 = width - 7;
		int var6 = var5 + 6;
		int var9;
		int var10;
		int var11;
		int var13;
		int var19;

		if (Mouse.isButtonDown(0)) {
			if (initialClickY == -1.0F) {
				boolean var7 = true;
				if (par2 >= top && par2 <= bottom) {
					int var8 = width / 2 - 110;
					var9 = width / 2 + 110;
					var10 = par2 - top - field_27261_r + (int) amountScrolled - 4;
					var11 = var10 / slotHeight;
					if (par1 >= var8 && par1 <= var9 && var11 >= 0 && var10 >= 0 && var11 < var4) {
						boolean var12 = var11 == selectedElement && System.currentTimeMillis() - lastClicked < 250L;
						elementClicked(var11, var12);
						selectedElement = var11;
						lastClicked = System.currentTimeMillis();
					}
					else if (par1 >= var8 && par1 <= var9 && var10 < 0) var7 = false;
					if (par1 >= var5 && par1 <= var6) {
						scrollMultiplier = -1F;
						var19 = getContentHeight() - (bottom - top - 4);
						if (var19 < 1) var19 = 1;
						var13 = (int) ((float) ((bottom - top) * (bottom - top)) / (float) getContentHeight());
						if (var13 < 32) var13 = 32;
						if (var13 > bottom - top - 8) var13 = bottom - top - 8;
						scrollMultiplier /= (float) (bottom - top - var13) / (float) var19;
					}
					else scrollMultiplier = 1F;
					if (var7) initialClickY = par2;
					else initialClickY = -2.0F;
				}
				else initialClickY = -2.0F;
			}
			else if (initialClickY >= 0.0F) {
				amountScrolled -= (par2 - initialClickY) * scrollMultiplier;
				initialClickY = par2;
			}
		}
		else {
			while (Mouse.next()) {
				int var16 = Mouse.getEventDWheel();

				if (var16 != 0) {
					if (var16 > 0) var16 = -3;
					else if (var16 < 0) var16 = 3;
					amountScrolled += var16 * slotHeight / 2;
				}
			}
			initialClickY = -1.0F;
		}

		bindAmountScrolled();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator var18 = Tessellator.instance;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/background.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var17 = 32.0F;
		var18.startDrawingQuads();
		var18.setColorOpaque_I(2105376);
		var18.addVertexWithUV(left, bottom, 0.0D, left / var17, (bottom + (int) amountScrolled) / var17);
		var18.addVertexWithUV(right, bottom, 0.0D, right / var17, (bottom + (int) amountScrolled) / var17);
		var18.addVertexWithUV(right, top, 0.0D, right / var17, (top + (int) amountScrolled) / var17);
		var18.addVertexWithUV(left, top, 0.0D, left / var17, (top + (int) amountScrolled) / var17);
		var18.draw();
		var9 = width / 2 - 92 - 16;
		var10 = top + 4 - (int) amountScrolled;

		int var14;

		for (var11 = 0; var11 < var4; ++var11) {
			var19 = var10 + var11 * slotHeight + field_27261_r;
			var13 = slotHeight - 4;

			if (var19 <= bottom && var19 + var13 >= top) {
				if (field_25123_p && isSelected(var11)) {
					var14 = width / 2 - 110;
					int var15 = width / 2 + 110;
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					var18.startDrawingQuads();
					var18.setColorOpaque_I(8421504);
					var18.addVertexWithUV(var14, var19 + var13 + 2, 0.0D, 0.0D, 1.0D);
					var18.addVertexWithUV(var15, var19 + var13 + 2, 0.0D, 1.0D, 1.0D);
					var18.addVertexWithUV(var15, var19 - 2, 0.0D, 1.0D, 0.0D);
					var18.addVertexWithUV(var14, var19 - 2, 0.0D, 0.0D, 0.0D);
					var18.setColorOpaque_I(0);
					var18.addVertexWithUV(var14 + 1, var19 + var13 + 1, 0.0D, 0.0D, 1.0D);
					var18.addVertexWithUV(var15 - 1, var19 + var13 + 1, 0.0D, 1.0D, 1.0D);
					var18.addVertexWithUV(var15 - 1, var19 - 1, 0.0D, 1.0D, 0.0D);
					var18.addVertexWithUV(var14 + 1, var19 - 1, 0.0D, 0.0D, 0.0D);
					var18.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}

				drawSlot(var11, var9, var19, var13, var18);
			}
		}

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		byte var20 = 4;
		overlayBackground(0, top, 255, 255);
		overlayBackground(bottom, height, 255, 255);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		var18.startDrawingQuads();
		var18.setColorRGBA_I(0, 0);
		var18.addVertexWithUV(left, top + var20, 0.0D, 0.0D, 1.0D);
		var18.addVertexWithUV(right, top + var20, 0.0D, 1.0D, 1.0D);
		var18.setColorRGBA_I(0, 255);
		var18.addVertexWithUV(right, top, 0.0D, 1.0D, 0.0D);
		var18.addVertexWithUV(left, top, 0.0D, 0.0D, 0.0D);
		var18.draw();
		var18.startDrawingQuads();
		var18.setColorRGBA_I(0, 255);
		var18.addVertexWithUV(left, bottom, 0.0D, 0.0D, 1.0D);
		var18.addVertexWithUV(right, bottom, 0.0D, 1.0D, 1.0D);
		var18.setColorRGBA_I(0, 0);
		var18.addVertexWithUV(right, bottom - var20, 0.0D, 1.0D, 0.0D);
		var18.addVertexWithUV(left, bottom - var20, 0.0D, 0.0D, 0.0D);
		var18.draw();
		var19 = getContentHeight() - (bottom - top - 4);

		if (var19 > 0) {
			var13 = (bottom - top) * (bottom - top) / getContentHeight();
			if (var13 < 32) var13 = 32;
			if (var13 > bottom - top - 8) var13 = bottom - top - 8;
			var14 = (int) amountScrolled * (bottom - top - var13) / var19 + top;
			if (var14 < top) var14 = top;
			var18.startDrawingQuads();
			var18.setColorRGBA_I(0, 255);
			var18.addVertexWithUV(var5, bottom, 0.0D, 0.0D, 1.0D);
			var18.addVertexWithUV(var6, bottom, 0.0D, 1.0D, 1.0D);
			var18.addVertexWithUV(var6, top, 0.0D, 1.0D, 0.0D);
			var18.addVertexWithUV(var5, top, 0.0D, 0.0D, 0.0D);
			var18.draw();
			var18.startDrawingQuads();
			var18.setColorRGBA_I(8421504, 255);
			var18.addVertexWithUV(var5, var14 + var13, 0.0D, 0.0D, 1.0D);
			var18.addVertexWithUV(var6, var14 + var13, 0.0D, 1.0D, 1.0D);
			var18.addVertexWithUV(var6, var14, 0.0D, 1.0D, 0.0D);
			var18.addVertexWithUV(var5, var14, 0.0D, 0.0D, 0.0D);
			var18.draw();
			var18.startDrawingQuads();
			var18.setColorRGBA_I(12632256, 255);
			var18.addVertexWithUV(var5, var14 + var13 - 1, 0.0D, 0.0D, 1.0D);
			var18.addVertexWithUV(var6 - 1, var14 + var13 - 1, 0.0D, 1.0D, 1.0D);
			var18.addVertexWithUV(var6 - 1, var14, 0.0D, 1.0D, 0.0D);
			var18.addVertexWithUV(var5, var14, 0.0D, 0.0D, 0.0D);
			var18.draw();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private void overlayBackground(int par1, int par2, int par3, int par4) {
		Tessellator var5 = Tessellator.instance;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/background.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var6 = 32.0F;
		var5.startDrawingQuads();
		var5.setColorRGBA_I(4210752, par4);
		var5.addVertexWithUV(0.0D, par2, 0.0D, 0.0D, par2 / var6);
		var5.addVertexWithUV(width, par2, 0.0D, width / var6, par2 / var6);
		var5.setColorRGBA_I(4210752, par3);
		var5.addVertexWithUV(width, par1, 0.0D, width / var6, par1 / var6);
		var5.addVertexWithUV(0.0D, par1, 0.0D, 0.0D, par1 / var6);
		var5.draw();
	}

	protected int getSize() {
		return parentGui.modChangelog.length;
	}

	protected void elementClicked(int var1, boolean var2) {}

	protected boolean isSelected(int var1) {
		return false;
	}

	protected int getContentHeight() {
		return getSize() * 12;
	}

	protected void drawBackground() {
		parentGui.drawDefaultBackground();
	}

	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) {
		parentGui.drawString(parentGui.fontRenderer(), parentGui.modChangelog[var1].replace("	", "    "), 8, var3, 0xFFFFFF);
	}

}
