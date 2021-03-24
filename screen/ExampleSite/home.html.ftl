

<div class="mdc-layout-grid">
    <div class="mdc-layout-grid__inner">
        <div class="mdc-layout-grid__cell">
            <img src="/images/MoquiLogoSmall.png" height="50">

            <#assign headerLogoList = sri.getThemeValues("STRT_HEADER_LOGO")>
            <#if headerLogoList?has_content>
                <img src="${sri.buildUrl(headerLogoList?first).getUrl()}" alt="Home" height="32">
            </#if>
        </div>
        <div class="mdc-layout-grid__cell">
            <h2>Welcome Home</h2>
        </div>
    </div>
</div>

<button class="mdc-button mdc-button--outlined"><span class="mdc-button__ripple"></span>Learn More</button>

<div class="mdc-text-field mdc-text-field--outlined">
    <input class="mdc-text-field__input" id="text-field-hero-input">
    <div class="mdc-notched-outline">
        <div class="mdc-notched-outline__leading"></div>
        <div class="mdc-notched-outline__notch">
            <label for="text-field-hero-input" class="mdc-floating-label">Name</label>
        </div>
        <div class="mdc-notched-outline__trailing"></div>
    </div>
</div>
